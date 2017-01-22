import logging
import random
import flask
import datetime

# Imports the Google Cloud client library
from google.cloud import datastore
from flask import Flask, request
from flask_cors import CORS, cross_origin

app = Flask(__name__)
CORS(app)

def list_messages(client):
    query = client.query(kind='Messages')
    query.order = ['timestamp']

    return list(query.fetch())

def create_client(project_id):
    return datastore.Client(project_id)

def add_message(client, text, latitude, longitude, timestamp, user_id):
    key = client.key('Messages')

    obj = datastore.Entity(
    key, exclude_from_indexes=['message'])

    obj.update({
	'message': text,
	'latitude': latitude,
	'longitude': longitude,
	'timestamp': timestamp,
	'user_id': user_id
    })

    client.put(obj)

    return obj.key

def delete_message(client, message_id):
    key = client.key('Messages', message_id)
    client.delete(key)

@app.route("/get", methods = ["GET"])
def get():
	datastore_client = create_client('hackucsc2017-156309')
	return flask.jsonify(list_messages(datastore_client))

@app.route("/post", methods = ["POST"])
def post():
   	text = request.form['message']
   	latitude = request.form['latitude']
   	longitude = request.form['longitude']
   	timestamp = datetime.datetime.utcnow()
   	user_id = request.form['user_id']

	# Instantiates a client
	datastore_client = create_client('hackucsc2017-156309')

        # Adds message
        add_message(datastore_client, text, latitude, longitude, timestamp, user_id)

	return text

@app.errorhandler(500)
def server_error(e):
    # Log the error and stacktrace.
    logging.exception('An error occurred during a request.')
    return 'An internal error occurred.', 500

if __name__ == "__main__":
    app.run()
