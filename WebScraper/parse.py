from scraper_coop import fetch_data as coop_data
from scraper_ica import fetch_data as ica_data
from scraper_willys import fetch_data as willys_data
from scraper_lidl import fetch_data as lidl_data

coop_data('coop_bergvik.html', 'Coop Bergvik')
coop_data('coop_norrstrand.html', 'Coop Norrstrand')
coop_data('coop_herrhagen.html', 'Coop Herrhagen')
coop_data('coop_kronoparken.html', 'Coop Kronoparken')
coop_data('coop_kroppkärr.html', 'Coop Kroppkärr')
coop_data('coop_karlstad.html', 'Coop Karlstad')
coop_data('coop_råtorp.html', 'Coop Råtorp')
coop_data('coop_strand.html', 'Coop Strand')
coop_data('coop_välsviken.html', 'Coop Välsviken')

ica_data('ica_wallinders.html', 'Ica Wallinders')
ica_data('ica_nära_kärnan.html', 'Ica Nära Kärnan')
ica_data('ica_stormarknad_karlstad.html', 'Ica Stormarknad Karlstad')
ica_data('ica_hagahallen.html', 'Ica Hagahallen')
ica_data('ica_skåre.html', 'Ica Skåre')
ica_data('ica_nära_viken.html', 'Ica Nära Viken')
ica_data('ica_nära_mossbergs.html', 'Ica Nära Mossbergs')
ica_data('ica_fanfaren.html', 'Ica Fanfaren')
ica_data('ica_välsviken.html', 'Ica Välsviken')

willys_data('willys_bryggudden.json', 'Willy:s Bryggudden')
willys_data('willys_växnås.json', 'Willy:s Växnås')

lidl_data('lidl_rattgatan.html', 'Lidl Rattgatan')
lidl_data('lidl_östra_infarten.html', 'Lidl Östra Infarten')