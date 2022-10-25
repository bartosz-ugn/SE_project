#!/usr/bin/env python
# -*- coding: utf-8 -*-

from encodings import utf_8
from io import TextIOWrapper

def isMatch_property(arr: list, property: str):
    return ''.join(arr[-1*len(property+'="'):]) == property + '="'

def isMatch_end(arr: list, char: str, property: str, isOn_line: bool):
    if property == 'textContent':
        return char == '<' and isOn_line == True
    else:
        return char == '"' and isOn_line == True

def isMatch_textContent(arr: list):
    return ''.join(arr[-2:]) == '">'

def isColonHyphen(arr: list):
    return ''.join(arr[-2:]) == ':-'

def write_to_out(arr: list, out: TextIOWrapper, isEnd: bool):
    out.write('|' + ''.join(arr))
    if isEnd == False:
        out.write('|,')
    else:
        out.write('|\n')

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

def fetch_data(html_file: str, store: str):
    f = open(html_file, encoding='utf-8')
    db = open('output.txt', 'a', encoding='utf-8')
    arr = [] # ORDNING: CATEGORY, IMG_URL, TITLE, CAMPAIGN, PRICE
    category = ''
    img_url = ''
    isOn_line = False
    isIn_scope = False
    isIn_price = False
    isDecimal = False
    isPrevious_match = False

    for line in f.readlines():
            if 'offer-category__header summary active' in line:
                isIn_price = True
                for char in line:
                    if isMatch_textContent(arr):
                        arr.clear()
                        isOn_line = True
                    if isMatch_end(arr, char, 'textContent', isOn_line):
                        category = ''.join(arr)
                        isOn_line = False
                        arr.clear()
                        break
                    arr.append(char) 

            if 'img class="lazy"' in line:
                #db.write('|' + store + '|,')
                #db.write('|' + category + '|,')
                for char in line:
                    if 'data-original=""' in line:
                        img_url = '||,'
                        break
                    if isMatch_property(arr, 'data-original'):
                        arr.clear()
                        isOn_line = True
                    if isMatch_end(arr, char, 'data-original', isOn_line):
                        img_url = ''.join(arr)
                        arr.clear()
                        isOn_line = False
                        break
                    arr.append(char)

            if 'offer-type__product-name' in line:
                isPrevious_match = True

            elif isPrevious_match == True:
                categoryFlag = False
                category = 'Diverse'

                if categoryFlag == False:
                    for item in frukt_och_grönsaker:
                        if item in line.lower():
                            category = 'Frukt & grönt'
                            categoryFlag = True
                        #break           
                if categoryFlag == False:
                    for item in kött:
                        if item in line.lower():
                            category = 'Kött'
                            categoryFlag = True
                        #break
                if categoryFlag == False:
                    for item in färdigmat:
                        if item in line.lower():
                            category = 'Färdigmat'
                            categoryFlag = True
                        #break        
                if categoryFlag == False:
                    for item in bröd:
                        if item in line.lower():
                            category = 'Bröd'
                            categoryFlag = True
                        #break
                if categoryFlag == False:
                    for item in mejeri:
                        if item in line.lower():
                            category = 'Mejeri'
                            categoryFlag = True
                        #break
                if categoryFlag == False:
                    for item in dryck:
                        if item in line.lower():
                            category = 'Dryck'
                            categoryFlag = True
                                    #break
                if categoryFlag == False:    
                    for item in skafferi:
                        if item in line.lower() or 'te">' in ''.join(arr[-4:]).lower():
                            category = 'Skafferi'
                            categoryFlag = True
                                    #break
                if categoryFlag == False:
                    for item in fryst:
                        if item in line.lower():
                            category = 'Fryst'
                            categoryFlag = True
                                    #break
                if categoryFlag == False:           
                    for item in husdjur:
                        if item in line.lower():
                            category = 'Husdjur'
                            categoryFlag = True
                                    #break
                if categoryFlag == False:
                    for item in skönhet_och_hälsa:
                        if item in line.lower():
                            category = 'Skönhet & hälsa'
                            categoryFlag = True
                                #break
                if categoryFlag == False:
                    category = 'Diverse'
                    categoryFlag = True

                db.write('|' + store + '|,')
                db.write('|' + category + '|,')
                db.write('|' + img_url + '|,')
                isPrevious_match = False
                for char in line:
                    if char != '\n':
                        arr.append(char)
                write_to_out(arr[16:], db, False)
                arr.clear()

            if 'product-price__price-items-wrapper' in line or isIn_scope == True:
                isIn_scope = True
                if 'product-price__amount' in line and isIn_scope == True:
                    for char in line:
                        if isMatch_textContent(arr):
                            arr.clear()
                            isOn_line = True
                        if isMatch_end(arr, char, 'textContent', isOn_line):
                            db.write('|' + ''.join(arr).replace('&#246;','ö') + '|,')
                            arr.clear()
                            isOn_line = False
                            isIn_scope = False
                            break
                        arr.append(char)
                elif 'product-price__price-values' in line and isIn_scope == True:
                    db.write('||,')
                    isIn_scope = False

            if 'div class="product-price__price-value"' in line and isIn_price == True:
                for char in line:
                    if isMatch_textContent(arr):
                        arr.clear()
                        isOn_line = True
                    if isMatch_end(arr, char, 'textContent', isOn_line):
                        if isColonHyphen(arr):
                            db.write('|' + ''.join(arr).replace(':-', '.00') + '|\n')
                        else:
                            db.write('|' + ''.join(arr) + '.')
                            isDecimal = True
                        isOn_line = False
                        isPrevious_match = False
                        arr.clear()
                        isIn_price == False
                        break
                    arr.append(char)
            if 'product-price__decimal' in line and isDecimal == True:
                for char in line:
                    if isMatch_textContent(arr):
                        arr.clear()
                        isOn_line = True
                    if isMatch_end(arr, char, 'textContent', isOn_line):
                        db.write('' + ''.join(arr) + '|\n')
                        isOn_line = False
                        isPrevious_match = False
                        arr.clear()
                        isIn_price == False
                        break
                    arr.append(char)
                 
    f.close()
    db.close()