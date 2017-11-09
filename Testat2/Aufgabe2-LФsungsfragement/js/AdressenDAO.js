/*
 * Data Access Object (Datenzugriff) für adresseen-Fachobjekte  ------
 */

class AdressenDAO {
	constructor() {
		this._adressenArray = [];
	}

	/* 
	 * laden und speichern in local storage
	 */
	speichern() {
		localStorage.setItem('adressenDAO', JSON.stringify(this));
	}

	laden() {
		var i;
		var gespeichertesDAOalsString = localStorage.getItem('adressenDAO');
		var gespeichertesDAO = JSON.parse(gespeichertesDAOalsString);
		
		if (gespeichertesDAO != null)
			this._adressenArray = gespeichertesDAO._adressenArray;
		
		for (i = 0; i < this._adressenArray.length; ++i) {
			this._adressenArray[i] = new AdresseDTO(
				this._adressenArray[i]._id,
				this._adressenArray[i]._name,
				this._adressenArray[i]._email,
				this._adressenArray[i]._ort,
				this._adressenArray[i]._plz,
				this._adressenArray[i]._strasse);
		}
		
		console.log('gespeichertesDAO: ', gespeichertesDAO);
	}

	/*
	 * Hilfsfunktionen
	 */

	/**
	 * Liefert true, wenn die übergebene Adresse dem Namensfilter und/oder dem Adressfilter entspricht.
	 * Beide Filter sind Präfixfilter, d.h. sie sind dann true, wenn 'name' oder 'ort' ein Präfix des
	 * entsprechenden Attributs der Adresse ist. Leere Filterwerte werden dabei ignoriert, d.h. der Filter 
	 * wird nicht geprüft.
	 * Beispiele: 
	 * 		name='P' und ort='Ing', adresse.name='Peter' und adresse.ort='Ingolstadt': Ergebnis = true
	 * 		('P'' ist Präfix von 'Peter', 'Ing'' ist Präfix von 'Ingolstadt'') 
	 * 		name='' und ort='I', adresse.name='Peter' und adresse.ort='ngolstadt': Ergebnis = false 
	 * 		(der Namensfilter wird nicht evaluiert, 'I'' ist nicht Präfix von 'ngolstadt'')
	 */
	filter(adresse, name, ort) {
		var namelen = document.getElementById("nameID").value.length;
		var ortlen = document.getElementById("ortID").value.length;
		var nameid = document.getElementById("nameID").value;
		var ortid = document.getElementById("ortID").value;
		if((nameid === adresse.name.substring(0, namelen) || nameid === "") && (ortid === adresse.ort.substring(0, ortlen) || ortid === ""))
		{
			return true;
		}
		
		// *** (2) ***
	};	
	 
	/**
	 * Gibt das übergebene AdresseDTO-Array 'liste'' sortiert nach 'sortierung' (= string-Wert 
	 * Name, Ort oder PLZ) zurück. Abhängig vom Wert von 'sortierung' wird eine passende sortierFunktion
	 * definiert, die dann für die Sortierung mit "sort" genutzt wird.
	 */
	sortiereAdressenListe(liste, sortierung) {
		
		if(sortierung === "Ort"){
			if (liste !== undefined) {
				liste.sort((a, b) =>{
					if ( a.ort < b.ort){
						return 1;
					} else if ( a.ort > b.ort) {
						return -1;
					} else {
						return 0;   
					}
				});
			}
		}else if(sortierung === "PLZ"){
			if (liste !== undefined) {
				liste.sort((a, b) =>{
					if ( a._plz < b._plz){
						return 1;
					} else if ( a._plz > b._plz) {
						return -1;
					} else {
						return 0;   
					}
				});
			}
		}else{
			if (liste !== undefined) {
			liste.sort((a, b) =>{
				if ( a.name < b.name){
					return 1;
				} else if ( a.name > b.name) {
					return -1;
				} else {
					return 0;   
				}
			});
		}
		}

		// *** (3) ***/
	}

	/*
	 * Methoden zum Zugriff auf die adresseenliste
	 */
	findeAdresseZuId(id) {
		this.laden();
		var p = this._adressenArray[id];
		
		return p;
	}
	
	findeAlle() {
		this.laden();
		var ergebnis = [];
		var i, j = 0;
		
		for (i = 0; i < this._adressenArray.length; ++i) {
			if (this._adressenArray[i].id != -1) {
				ergebnis[j++] = this._adressenArray[i];
			}
		}
				
		ergebnis.sort(
			function(p1, p2) { 
				return p1.id - p2.id;
			}
		);

		return ergebnis;
	}

	findeZuFilterUndSortiere(name, ort, sortierung) {
		this.laden();
		var ergebnis = [];
		var i, j = 0;
		
		for (i = 0; i < this._adressenArray.length; ++i) {
			if (this._adressenArray[i].id != -1) {
				if (this.filter(this._adressenArray[i], name, ort)) { 
					ergebnis[j++] = this._adressenArray[i];
				}
			}
		}
				
		this.sortiereAdressenListe(ergebnis, sortierung);
		return ergebnis;
	}

	neueAdresse(adresse) {
		this.laden();
		var i;
		
		for (i = 0; i < this._adressenArray.length; ++i) {
			if (this._adressenArray[i].id == -1) {
				break;
			}
		}
		adresse.id = i;
		this._adressenArray[adresse.id] = adresse;
		this.speichern();
	}

	aktualisiereAdresse(adresse) {
		this.laden();
		if (this.findeAdresseZuId(adresse.id) !== "undefined") {
			this._adressenArray[adresse.id] = adresse;
			this.speichern();
		}
	};

	/**
	 * Löscht das Adressobjekt mit der übergebenen ID.
	 * Es wird nur "logisch" gelöscht, indem die id auf den Wert -1 gesetzt wird.
	 */
	loescheAdresse(id) {
		id = -1;
		/*var row = document.getElementById(id);
		var table = row.parentNode;
		while( table && table.tagName != 'TABLE')
			table = table.parentNode;
		if(!table)
			return;
		table.loescheAdresse(row.rowIndex);
		
		*/
		
		// *** (4) ***
	}

	/*
	* Getter für adresseDAO ---------------------------------------------
	*/
	static gibAdresseDAO() {
		var dao = "undefined";
		
		if (typeof(Storage) !== "undefined") {
			dao = new AdressenDAO();		
			if (localStorage['adressenDAO']) {
				try {
					var i;
					
					dao.laden();
					for (i = 0; i < dao._adressenArray.length; ++i) {
						var p = dao._adressenArray[i]; 
						console.log(p.toString());
					}

				} catch (error) {
					console.log(error);
				}
			}
		} else {
			alert("Sorry, your browser does not support web storage…");
		}	
		
		return dao;
	}
}
