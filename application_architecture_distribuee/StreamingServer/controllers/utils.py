import os

FILES_DIR = 'tracks'
def doesFileExists(filename):
    return os.path.isfile('./{0}/{1}'.format(FILES_DIR, filename))