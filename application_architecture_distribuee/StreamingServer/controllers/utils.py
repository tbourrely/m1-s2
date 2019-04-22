import os

FILES_DIR = os.getenv('FILES_DIR')
def doesFileExists(filename):
    return os.path.isfile('./{0}/{1}'.format(FILES_DIR, filename))