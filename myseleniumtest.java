import time

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options

options = Options()
        driver = webdriver.Chrome(options=options)
        username='admin'
        password='765'
        try:
        driver.get("http://localhost:8080")

        # Username
        driver.find_element(By.XPATH, "/html/body/div/div/form/div[1]/input").send_keys(username)

        # Password
        driver.find_element(By.XPATH, "/html/body/div/div/form/div[2]/input").send_keys(password)

        # Login
        driver.find_element(By.XPATH, "/html/body/div/div/form/input").click()
        time.sleep(1)

        # Profile
        driver.find_element(By.XPATH,'/html/body/section/div/nav/div/div/ul[2]/li[2]/a').click()


        finally:
        print("Test was successful")
        driver.quit()
