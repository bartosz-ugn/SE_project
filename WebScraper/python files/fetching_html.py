#!/usr/bin/env python
# -*- coding: utf-8 -*-
import os
from time import sleep
import wget
from dataclasses import replace
from encodings import utf_8
from io import TextIOWrapper
from selenium import webdriver
from selenium.webdriver.common.by import By

def fetching(html_url: str, html_file: str):
    wget.download(html_url, html_file)

def html_parsing(html_url: str, html_name: str):
    
    driver = webdriver.Chrome()
    driver = webdriver.Chrome()
    driver.maximize_window()
    driver.get(html_url)

    driver.find_element(By.CSS_SELECTOR,'.cmpboxbtnyes').click()
    sleep(2)
    for button in driver.find_elements(By.CSS_SELECTOR, '.Button.Button--invertedGreenDark.Button--radius.Button--small.Button--negativeMargin.Button--shadow.js-store-offers-block-toggler-trigger'):
        button.click()

    with open(html_name, 'w', encoding='utf-8') as f:
        f.write(driver.page_source)

    f.close()
    driver.close()

file_list = ['coop_bergvik.html', 'coop_norrstrand.html', 'coop_herrhagen.html', 'coop_kronoparken.html', 'coop_kroppkärr.html', 'coop_karlstad.html', 'coop_råtorp.html', 'coop_strand.html', 'coop_välsviken.html', 'willys_bryggudden.json', 'willys_växnås.json', 'lidl_rattgatan.html', 'lidl_östra_infarten.html', 'ica_wallinders.html', 'ica_nära_kärnan.html', 'ica_stormarknad_karlstad.html', 'ica_hagahallen.html', 'ica_skåre.html', 'ica_nära_viken.html', 'ica_nära_mossbergs.html', 'ica_fanfaren.html', 'ica_välsviken.html', 'output.txt']

for file in file_list:
    os.remove(file)
    
# COOP
html_parsing('https://www.coop.se/butiker-erbjudanden/stora-coop/stora-coop-bergvik/', 'coop_bergvik.html')###
html_parsing('https://www.coop.se/butiker-erbjudanden/coop/coop-norrstrand/', 'coop_norrstrand.html')
html_parsing('https://www.coop.se/butiker-erbjudanden/coop/coop-herrhagen/', 'coop_herrhagen.html')###
html_parsing('https://www.coop.se/butiker-erbjudanden/coop/coop-kronoparken/', 'coop_kronoparken.html')###
html_parsing('https://www.coop.se/butiker-erbjudanden/coop/kroppkarr/', 'coop_kroppkärr.html')###
html_parsing('https://www.coop.se/butiker-erbjudanden/coop/coop-city-karlstad/', 'coop_karlstad.html')###
html_parsing('https://www.coop.se/butiker-erbjudanden/coop-butiker/coop-ratorp-karlstad/', 'coop_råtorp.html')###
html_parsing('https://www.coop.se/butiker-erbjudanden/coop-butiker/coop-vasterstrand/', 'coop_strand.html')###
html_parsing('https://www.coop.se/butiker-erbjudanden/stora-coop/stora-coop-valsviken/', 'coop_välsviken.html')###

# WILLYS
fetching('https://www.willys.se/search/campaigns/offline?page=0&q=2290:campaignType:GENERAL&size=200&type=PERSONAL_GENERAL', 'willys_bryggudden.json')
fetching('https://www.willys.se/search/campaigns/offline?page=0&q=2117:campaignType:GENERAL&size=200&type=PERSONAL_GENERAL', 'willys_växnås.json')

# LIDL
fetching('https://www.lidl.se/veckans-erbjudanden', 'lidl_rattgatan.html')
fetching('https://www.lidl.se/veckans-erbjudanden', 'lidl_östra_infarten.html')

# ICA
fetching('https://www.ica.se/butiker/supermarket/karlstad/ica-supermarket-wallinders-871/erbjudanden/', 'ica_wallinders.html')
fetching('https://www.ica.se/butiker/nara/karlstad/ica-nara-karnan-868/butikserbjudanden/', 'ica_nära_kärnan.html')
fetching('https://www.ica.se/butiker/maxi/karlstad/maxi-ica-stormarknad-karlstad-11010/erbjudanden/', 'ica_stormarknad_karlstad.html')
fetching('https://www.ica.se/butiker/supermarket/karlstad/ica-supermarket-haga-865/erbjudanden/', 'ica_hagahallen.html')
fetching('https://www.ica.se/butiker/supermarket/karlstad/ica-supermarket-skare-877/erbjudanden/', 'ica_skåre.html')
fetching('https://www.ica.se/butiker/nara/karlstad/ica-nara-viken-866/butikserbjudanden/', 'ica_nära_viken.html')
fetching('https://www.ica.se/butiker/nara/karlstad/ica-nara-mossbergs-874/butikserbjudanden/', 'ica_nära_mossbergs.html')
fetching('https://www.ica.se/butiker/supermarket/karlstad/ica-supermarket-fanfaren-16619/erbjudanden/', 'ica_fanfaren.html')
fetching('https://www.ica.se/butiker/maxi/karlstad/maxi-ica-stormarknad-valsviken-13584/erbjudanden/', 'ica_välsviken.html')