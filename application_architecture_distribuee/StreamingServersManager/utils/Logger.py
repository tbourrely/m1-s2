from pathlib import Path  # python3 only
from datetime import datetime

class Logger():
    def __init__(self, name):
        today = datetime.today().strftime('%Y-%m-%d')
        self.filename = name + '-' + today + '.log'
        self.directory = Path('.') / 'logs'
    
    def write(self, message):
        with open(self.directory / self.filename, 'a') as file:
            now = datetime.now().strftime('%H:%M:%S')
            file.write(now + ' | ' + message + "\n")
            file.close()