import os

FILES_DIR = os.getenv('FILES_DIR')
def doesFileExists(filename):
    return os.path.isfile('./{0}/{1}'.format(FILES_DIR, filename))


EXT_MIMETYPES = {
  '.mp3': 'audio/mpeg',
  '.aac': 'audio/aac'
}
def filename2mime(filename):
  ext = os.path.splitext(filename)[1]
  return EXT_MIMETYPES.get(ext)