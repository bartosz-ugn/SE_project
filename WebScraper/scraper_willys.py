#!/usr/bin/env python
# -*- coding: utf-8 -*-

from encodings import utf_8
import json

frukt_och_grönsaker = ['ananas', 'sharon', 'aprikos', 'mor', 'banan', 'broccoli', 'druv', 'dadel', 'fikon', 'äpple', 'guava', 'frukt', 'plommon', 'kiwi', 'tomat', 'mango', 'nektarin', 'oliv', 'papaya', 'persika', 'pumpa', 'päron', 'russin', 'äpple', 'hallon', 'lingon', 'bär', 'hjortron', 'jordgubbe', 'jordgubbar', 'persimon', 'smultron', 'apelsin', 'citron', 'clementin', 'frukt', 'lime', 'satsuma', 'tangerin', 'avokado', 'aubergine', 'melon', 'quat', 'kål', 'sallad', 'lök', 'persilja', 'spenat', 'sallat', 'mor', 'ingefära', 'mandel', 'potatis', 'bön', 'rädisa', 'beta', 'gurka', 'ärt', 'majs', 'paprika', 'rabarber', 'selleri', 'sparris', 'squash']
kött = ['färs', 'lax', 'stek', 'räk', 'prosciutto', 'salami', 'skinka', 'ribs', 'filé', 'biff', 'kyckling', 'korv', 'wurst', 'kassler', 'fläsk', 'fisk']
färdigmat = ['färdig', 'chutney', 'soppa', 'gryta', 'lasagne', 'mayo', 'dip', 'snack pot', 'dressing', 'sås', 'pudding', 'hummus', 'tortellini', 'gnocchi', 'coleslaw', 'tzatziki', 'rätt', 'pastej', 'pannkak', 'chark']
bröd = ['bröd', 'grova', 'levain','limpa', 'ciabatta', 'fajita', 'krutong', 'tekaka', 'wasa', 'sandwich', 'focaccia', 'panini']
mejeri = ['bregott', 'crème', 'riven', 'mascarpone', 'extra', 'ägg', 'ost', 'yog', 'mjölk', 'kefir', 'crème fraiche', 'fil', 'grädde', 'smör', 'kvarg']
fryst = ['fryst', 'glass', 'wokmix', 'dumpling', 'pizza', 'färdig', 'skav', 'sparris', 'kyckling']
skafferi = ['nöt', 'kex', 'champinjoner', 'kanel', 'chips', 'plopp', 'smash', 'toffee', 'corn sticks', 'ballerina', 'bull', 'panko', 'havre', 'polly', 'kaffe', 'tex mex', 'tagliatelle', 'nudlar', 'ris', 'chips', 'salsa', 'soy', 'soj', 'pesto', 'pasta', 'tepås', 'socker', 'krydd', 'godis', 'marabou', 'choklad']
dryck = ['dryck', 'shot', 'smoothie', 'vatten', 'smaksättning', 'cider', 'kolsyra', 'öl', 'juice', 'läsk']
husdjur = ['katt', 'hund']
skönhet_och_hälsa = ['tvål', 'hår', 'spray', 'tampong', 'deo', 'gel', 'binda', 'schampo', 'protein', 'bar', 'tablett', 'brus', 'tand', 'medicin']

def fetch_data(file: str, store: str):

    db = open('output.txt', 'a', encoding='utf-8')

    with open(file, 'r', encoding='utf-8') as f:
        data = json.load(f)

    i = 0
    category = ''
    title = ''
    text = []
    hasCategory = False
    categoryFlag = False
    try:
        while(True):
            category = 'Diverse'
            hasCategory = False
            categoryFlag = False
            title = data['results'][i]['potentialPromotions'][0]['name']

            db.write('|' + store + '|,')

            if categoryFlag == False:
                for item in frukt_och_grönsaker:
                    if item in str(title).lower():
                        category = 'Frukt & grönsaker'
                        categoryFlag = True
                        break
            if categoryFlag == False:
                for item in kött:
                    if item in str(title).lower():
                        category = 'Kött'
                        categoryFlag = True
                        break
            if categoryFlag == False:
                for item in färdigmat:
                    if item in str(title).lower():
                        category = 'Färdigmat'
                        categoryFlag = True
                        break
            if categoryFlag == False:
                for item in bröd:
                    if item in str(title).lower():
                        category = 'Bröd'
                        categoryFlag = True
                        break
            if categoryFlag == False:
                for item in mejeri:
                    if item in str(title).lower():
                        category = 'Mejeri'
                        categoryFlag = True
                        break
            if categoryFlag == False:
                for item in fryst:
                    if item in str(title).lower():
                        category = 'Fryst'
                        categoryFlag = True
                        break
            if categoryFlag == False:
                for item in skafferi:
                    if item in str(title).lower() or str(title).lower() == 'te':
                        category = 'Skafferi'
                        categoryFlag = True
                        break
            if categoryFlag == False:
                for item in dryck:
                    if item in str(title).lower():
                        category = 'Dryck'
                        categoryFlag = True
                        break
            if categoryFlag == False:
                for item in husdjur:
                    if item in str(title).lower():
                        category = 'Husdjur'
                        categoryFlag = True
                        break
            if categoryFlag == False:
                for item in skönhet_och_hälsa:
                    if item in str(title).lower():
                        category = 'Skönhet & hälsa'
                        categoryFlag = True
                        break
            if categoryFlag == False:
                category = 'Diverse'
                categoryFlag = True
            
            db.write('|' + category + '|,')

            db.write('|' + data['results'][i]['image']['url'] + '|,')

            db.write('|' + data['results'][i]['potentialPromotions'][0]['name'] + '|,')

            for char in ''.join(data['results'][i]['potentialPromotions'][0]['cartLabel']).replace('Välj & blanda! ', ''):
                if ''.join(text[-3:]) == 'för':
                    db.write('|' + ''.join(text) + '|,')
                    hasCategory = True
                    break
                text.append(char)
            if hasCategory == False:
                db.write('|NULL|,')
            
            db.write('|' + ''.join(data['results'][i]['potentialPromotions'][0]['cartLabel']).replace('/st', '').replace('/kg', '').replace('Välj & blanda! ', '').replace(''.join(text) + ' ', '').replace(' ', '').replace(',','.') + '|\n')
            text.clear()
            i += 1
    except:
        f.close()
        db.close()