from wit import Wit
from pydub import AudioSegment
import pathlib, os

client = Wit(os.getenv("WIT_ACCESS_TOKEN"))

def getIntentFromFile(fileName):
    """Send the content of an audio file to WIT.AI
    
    Arguments:
        fileName {string} -- the file shoudl be a 3gp type, atm it is the only supported format
    
    Returns:
        json -- WIT.AI response
    """
    path = pathlib.Path(fileName)

    if not path.exists():
        raise "Files does not exists"

    audio = AudioSegment.from_file(fileName, "3gp")
    rate = audio.frame_rate

    return client.speech(audio.raw_data, None, {'Content-Type': 'audio/raw;encoding=signed-integer;bits=32;rate='+str(rate)+';endian=little'})