export class Company
{
    constructor (public id ?:number, public name?:string, public password?:string, public email?:string)
    {

    }
    get Password(): string
    {
        return this.password;
    }
    
}