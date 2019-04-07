from dotenv import load_dotenv
import os

class Settings:
    def __init__(self, *args, **kwargs):
        load_dotenv()
        
    def get(self, name):
        return os.getenv(name)