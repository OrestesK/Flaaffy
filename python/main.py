from initDriver import initDriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import sys

#init
driver = initDriver().getDriver()
driver.get(sys.argv[1])

#getting and storing data
WebDriverWait(driver, 5).until(EC.presence_of_element_located((By.XPATH, sys.argv[2])))
content = driver.find_element(By.XPATH, sys.argv[2])
imageUrl = driver.find_element(By.XPATH, '/html/body/div/div[2]/main/div[1]/div[1]/a/img').get_attribute('src')

#sout
print(content.text)
print(imageUrl)
driver.quit()
sys.exit()