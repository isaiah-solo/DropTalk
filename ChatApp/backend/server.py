import logging

from flask import Flask
app = Flask(__name__)

@app.route("/get", methods = ["GET"])
def get():
    return "Hello World"

@app.route("/post", methods = ["POST"])
def post():
    return "Hello World"

@app.errorhandler(500)
def server_error(e):
    # Log the error and stacktrace.
    logging.exception('An error occurred during a request.')
    return 'An internal error occurred.', 500

if __name__ == "__main__":
    app.run()
