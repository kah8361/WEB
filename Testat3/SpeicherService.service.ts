import { Injectable } from '@angular/core';
import { Person } from '../models/Person';

@Injectable()
export class SpeicherService {
    private imgPath = '/assets/img/';

    public constructor() {
    }

    public ladePerson(): Person {
        let personStr: string = localStorage.getItem('daten');
        let person: Person = null;

        if (personStr === null || personStr === "") {
            person = new Person(
                    'Kathi',
                    '777',
                    'Bart Simpson',
                    this.imgPath + 'bart.jpeg',
                    '2003-12-24',
                    'Hollywood',
                    'Sohn'
            );
            localStorage.setItem('daten', JSON.stringify(person));
            console.debug("SpeicherService.ladeDaten: localStorage empty, person = " + JSON.stringify(person));
            return person;
        } else {
            try {
                person = JSON.parse(personStr);
                console.debug("SpeicherService.ladeDaten: person from localStorage = " + JSON.stringify(person));
                return person;
            } catch (e) {
                console.error("SpeicherService.ladePerson: error: " + e);
            }

        }
    }

    public speicherePerson(person: Person): void {
        try {
            localStorage.setItem('daten', JSON.stringify(person));
            console.debug("SpeicherService.speicherePerson: writing to localStorage: " 
                + JSON.stringify(person));
        } catch (e) {
            console.error("SpeicherService.speicherePerson: error: " + e);
        }
    }
}
