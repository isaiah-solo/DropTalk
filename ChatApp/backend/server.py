import logging

# Imports the Google Cloud client library
from google.cloud import datastore
from flask import Flask
app = Flask(__name__)

counter=0

@app.route("/get", methods = ["GET"])

def create_client(project_id):
	return datastore.Client(project_id)

def add_message(client, description):
	key = client.key('Message')

	message = datastore.Entity(
	key, exclude_from_indexes=['description'])

	message.update({
		'created': datetime.datetime.utcnow(),
		'description': description,
		'done': False
	})

	client.put(message)

	return message.key

def delete_message(client, message_id):
	key = client.key('Message', message_id)
	client.delete(key)

def get():
    return "Hello World"

@app.route("/post", methods = ["POST"])
def post():
	message=''
	# Instantiates a client
	datastore_client = create_client('hackucsc2017-156309')

	# The kind for the new entity
	kind = 'Message'
	# The name/ID for the new entity
	name = str(counter++)
	# The Cloud Datastore key for the new entity
	task_key = datastore_client.key(kind, name)

	# Prepares the new entity
	task = datastore.Entity(key=task_key)
	task['description'] = message

	# Saves the entity
	datastore_client.put(task)

	print('Saved {}: {}'.format(task.key.name, task['description']))
	return "Hello World"


@app.errorhandler(500)
def server_error(e):
    # Log the error and stacktrace.
    logging.exception('An error occurred during a request.')
    return 'An internal error occurred.', 500

if __name__ == "__main__":
    app.run()
