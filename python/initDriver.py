from selenium import webdriver
from selenium.webdriver.chrome.options import Options
class initDriver():
    def __init__(self):
        chrome_options = Options()
        chrome_options.add_argument("--disable-extensions")
        chrome_options.add_argument("--disable-gpu")
        chrome_options.add_argument("--no-sandbox")
        chrome_options.add_argument("--headless")
        driver = webdriver.Chrome(options=chrome_options)
        self.driver = driver
    def getDriver(self):
        return self.driver


