import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common'; import { FormsModule } from '@angular/forms'; import { HttpClient } from '@angular/common/http'; import { API } from '../api';
@Component({selector:'app-stores',standalone:true,imports:[CommonModule,FormsModule],templateUrl:'./stores.html'})
export class Stores implements OnInit {
 items:any[]=[]; item:any={ name: '', address: '', city: '', phone: '' }; message='';
 constructor(private http:HttpClient, private cdr:ChangeDetectorRef){}
 ngOnInit(){this.load();}
 load(){this.http.get<any[]>(`${API}/stores`).subscribe({next:d=>{this.items=d;this.cdr.detectChanges();},error:e=>this.message='Cannot connect to backend'});}
 add(){this.http.post(`${API}/stores`,this.item).subscribe({next:()=>{this.message='Store added successfully'; this.reset(); this.load();},error:e=>this.message='Unable to save record'});}
 delete(id:number){if(confirm('Delete this record?')) this.http.delete(`${API}/stores/${id}`,{responseType:'text'}).subscribe(()=>this.load());}
 reset(){this.item={ name: '', address: '', city: '', phone: '' };}
}