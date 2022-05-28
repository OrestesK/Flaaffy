from initDriver import initDriver
import sys
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.common.keys import Keys
driver = initDriver().getDriver()
driver.get(sys.argv[1])
WebDriverWait(driver, 5).until(EC.presence_of_element_located((By.XPATH, '//*[@id="content"]/main/div[1]/div[2]/p[2]')))
thing = driver.find_element(By.XPATH, sys.argv[2])
print(thing.text)
driver.quit()
sys.exit()