from initDriver import initDriver
import sys
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.common.keys import Keys
import requests
import base64
driver = initDriver().getDriver()
driver.get(sys.argv[1])
WebDriverWait(driver, 5).until(EC.presence_of_element_located((By.XPATH, sys.argv[2])))
content = driver.find_element(By.XPATH, sys.argv[2])
imageUrl = driver.find_element(By.XPATH, '/html/body/div/div[2]/main/div[1]/div[1]/a/img').get_attribute('src')
print(content.text)
print(imageUrl)
driver.quit()
sys.exit()