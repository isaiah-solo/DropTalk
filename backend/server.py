import logging
import random
import flask
import datetime

# Imports the Google Cloud client library
from google.cloud import datastore
from flask import Flask, request
from flask_cors import CORS, cross_origin
from google.appengine.ext import ndb
from math import radians, cos, sin, asin, sqrt

app = Flask(__name__)
CORS(app)

def haversine(lon1, lat1, lon2, lat2):
    """
    Calculate the great circle distance between two points 
    on the earth (specified in decimal degrees)
    """
    # convert decimal degrees to radians 
    lon1, lat1, lon2, lat2 = map(radians, [lon1, lat1, lon2, lat2])

    # haversine formula 
    dlon = lon2 - lon1 
    dlat = lat2 - lat1 
    a = sin(dlat/2)**2 + cos(lat1) * cos(lat2) * sin(dlon/2)**2
    c = 2 * asin(sqrt(a)) 
    r = 6371 # Radius of earth in kilometers. Use 3956 for miles
    return c * r

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

def delete_messages(client):
    query = client.query(kind='Messages')
	timeUntilOld = datetime.datetime.utcnow() - 30
	query.add_filter('timestamp', '>=',timeUntilOld )
    list_of_entities = list(query.fetch())
	list_of_keys = ndb.put_multi(list_of_entities)
	list_of_entities = ndb.get_multi(list_of_keys)
	ndb.delete_multi(list_of_keys)

@app.route("/postToGet", methods = ["POST"])
def postToGet():
	myLon = request.form['longitude']
	myLat = request.form['latitude']
	datastore_client = create_client('hackucsc2017-156309')
	listOfMessages = list_messages(data_storeclient)
	newList = {}
	for message in listOfMessages:
		distance = haversine(myLon, myLat, message['longitude'],messages['latitude'])
		# get distance between 
		# myLon, myLat and message['longitude'],messages['latitude']
		# if it's shorter than 50 ft return
		if distance < 0.0009144:
			newList.append(message)
	return flask.jsonify(newList)

@app.route("/get", methods = ["GET"])
def get():
  datastore_client = create_client('hackucsc2017-156309')
  return flask.jsonify(list_messages(datastore_client))

@app.route("/post", methods = ["POST"])
def post():
	datastore_client = create_client('hackucsc2017-156309')
   	delete_messages(datastore_client) # delete messages that are old from the DB

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
