import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common'; import { FormsModule } from '@angular/forms'; import { HttpClient } from '@angular/common/http'; import { API } from '../api';
@Component({selector:'app-customers',standalone:true,imports:[CommonModule,FormsModule],templateUrl:'./customers.html'})
export class Customers implements OnInit {
 items:any[]=[]; item:any={ name: '', email: '', phone: '', address: '' }; message='';
 constructor(private http:HttpClient, private cdr:ChangeDetectorRef){}
 ngOnInit(){this.load();}
 load(){this.http.get<any[]>(`${API}/customers`).subscribe({next:d=>{this.items=d;this.cdr.detectChanges();},error:e=>this.message='Cannot connect to backend'});}
 add(){this.http.post(`${API}/customers`,this.item).subscribe({next:()=>{this.message='Customer added successfully'; this.reset(); this.load();},error:e=>this.message='Unable to save record'});}
 delete(id:number){if(confirm('Delete this record?')) this.http.delete(`${API}/customers/${id}`,{responseType:'text'}).subscribe(()=>this.load());}
 reset(){this.item={ name: '', email: '', phone: '', address: '' };}
}