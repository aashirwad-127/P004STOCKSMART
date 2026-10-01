import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common'; import { FormsModule } from '@angular/forms'; import { HttpClient } from '@angular/common/http'; import { API } from '../api';
@Component({selector:'app-products',standalone:true,imports:[CommonModule,FormsModule],templateUrl:'./products.html'})
export class Products implements OnInit {
 items:any[]=[]; item:any={ sku: '', name: '', price: 0, quantity: 0, reorderLevel: 0, barcode: '' }; message='';
 constructor(private http:HttpClient, private cdr:ChangeDetectorRef){}
 ngOnInit(){this.load();}
 load(){this.http.get<any[]>(`${API}/products`).subscribe({next:d=>{this.items=d;this.cdr.detectChanges();},error:e=>this.message='Cannot connect to backend'});}
 add(){this.http.post(`${API}/products`,this.item).subscribe({next:()=>{this.message='Product added successfully'; this.reset(); this.load();},error:e=>this.message='Unable to save record'});}
 delete(id:number){if(confirm('Delete this record?')) this.http.delete(`${API}/products/${id}`,{responseType:'text'}).subscribe(()=>this.load());}
 reset(){this.item={ sku: '', name: '', price: 0, quantity: 0, reorderLevel: 0, barcode: '' };}
}