export interface User {
  id: number;
  name: string;
  email: string;
  role: 'ADMIN' | 'USER'  ; 
  address: string;
  createdAt: string; 


}export class User {
  id: number;

  username: string;
  email: string;
  phone: string;
 

  constructor(
    id: number = 0,

    username: string = '',
    email: string = '',
    phone: string = ''
   
 
  ) {
    this.id = id;
   
    this.username = username;
    this.email = email;
    this.phone = phone;
   

  }
}