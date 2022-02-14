# Weekly Updates on Project

Tiedosto päivitetään maanantaisin

### Viikko 5.

Projektin GUI:n runko on suunniteltu ja pääosin koodattu. Alustava tietokantakaavio lisätty README.md tiedostoon. Appi käynnistyy, sille on luotu custom applikaatio ikkuna ja ikkunaan on lisätty menubar. Menubarin kautta voidaan valita kolmen näkymän välillä: "Files -> Show All" perusnäkymä, "Help -> User Guide" ja "Help -> Keybinds". Jälkimmäiset lataavat appData kansiossa olevat guide.html ja keybinds.html tiedostot ja näyttävät niiden sisällön käyttäjälle. HTML-tiedoston nättäminen -toiminnallisuus on valmis ja riittää, että projektin lopussa html-tiedosto päivitetään ajan tasalle.

To ja Pe menivät Github-Eclipse integraation opettelussa ja Java AWT ja Swing frameworkeihin tutustuessa. Ma sovellus oli edellisessä kappaleessa kuvatussa tilassa. Sain viikolla hahmotettua kuinka AWT:tä ja Swingiä voidaan käyttää ja luotua GUI:n rungon. Viikon 6. tavoiteena on saada oleellisimmat suunnitelmassa kuvatut parserointi-toiminnallisuudet toteutettua appiin (sisältäen valitun XML-tiedoston näyttämisen käytäjälle).

### Viikko 6.

Sunnitelman tavoitteet viikolle 6. savutettiin. GUI:n kaikki oleelliset komponentit ovat valmiit. Sovellukseen voi lisätä menubarin kautta yhden tai useamman XML-tiedoston yhdellä kertaa. Jokaiselle lisätylle tiedostolle ilmestyy "radio button" ja sen vieren tavallinen painike tiedoston mukaan nimettynä. Kun tiedoston nimeä klikkaa, avautuu kyseinen XML-tiedosto uuteen ikkunaan (tiedostoja voi avata useita kerralla). Radio button voi olla valittuna vain yhden tiedoston kohdalla kerrallaan. Kun ala reunassa olevaa "Run Parser" nappia painaa, ohjelma suorittaa suppean parsinnan "radio buttonin" osoittamalle tiedostolle. Tulokset printataan toistaiseksi consoliin. Vastaavasti painamalla "Delete file" nappia, ohjelma poistaa valitun radio buttonin osoittaman tiedoston sovellus-näkymästä. Menubarissa on lisäksi "Delete All" komento, joka poistaa kaikki tiedostot kerralla.

Puuttuvia toiminnallisuuksia on vielä parsinnan laajentaminen kuukausittaisen datan tapauksiin, parsinnan tulosten näyttäminen uudessa ikkunassa (voidaan käyttää valmista luokkaa pohjana) ja keybindejen lisääminen. Parsinta suoritetaan jo nyt Javan (org.w3c.dom) kirjaston avulla, joten laajennus tukemaan kuukausi-malli pitäisi sujua kivuttomasti. Luokkien nimeämistä on refaktotoitu selvemmäksi ja lopulliset muutokset päivitetään luokkakaavioon viimeistään seuraavan viikon palautuksessa.

XML-tiedostoihin on lisätty yksi <balance> root-node, koska käytetty kirjasto vaati tätä. Tämän ei ole ongelma, vaikka ohjelman spekseissä vaadittaisiin ehdottomasti aikaisempaa tiedostomuotoa. Voimme tässä tapauksessa yksinkertaisesti kirjoittaa koodin, joka lisää root noden tiedostoon, ennen kuin tiedosto luetaan DOM-formaattiin. 
