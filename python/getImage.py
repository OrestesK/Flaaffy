import sys
import urllib
import base64

image = urllib.request.urlopen(sys.argv[0]).read()
print(base64.b64encode(image))
driver.quit()
sys.exit()