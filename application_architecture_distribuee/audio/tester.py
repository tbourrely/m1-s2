from wit import Wit

client = Wit("UOHJI4SSJ4C3AFIUDBTXX5TFGMWC3KQH")
# resp = client.message("arrete la musique")

# print(str(resp))


resp = None

with open('pause-track.wav', 'rb') as file:
    resp = client.speech(file, None, {'Content-Type': 'audio/wav'})

print(str(resp))
