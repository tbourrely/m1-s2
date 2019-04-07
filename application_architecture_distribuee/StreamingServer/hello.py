from flask import Flask, Response
app = Flask(__name__)

@app.route("/aac")
def streamwav():
    def generate():
        with open("Arose.aac", "rb") as fwav:
            fwav.seek(3384082)
            data = fwav.read(1024)
            while data:
                yield data
                data = fwav.read(1024)
            print("done")
            fwav.close()
    return Response(generate(), mimetype="audio/aac")
if __name__ == '__main__':
    app.run(debug=True)
