export class Person {
    public username: string;
    public password: string;
    public name: string;
    public picturePath: string;
    public birthdate: string;
    public bornIn: string;
    public profession: string;

    public constructor(username: string, password: string, name: string, path: string, birthdate: string, bornIn: string, 
            profession: string) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.picturePath = path;
        this.birthdate = birthdate;
        this.bornIn = bornIn;
        this.profession = profession;
    }
}