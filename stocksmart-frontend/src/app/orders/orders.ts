import { Component,OnInit,ChangeDetectorRef } from '@angular/core'; import { CommonModule } from '@angular/common'; import { FormsModule } from '@angular/forms'; import { HttpClient } from '@angular/common/http'; import { API } from '../api';
@Component({selector:'app-orders',standalone:true,imports:[CommonModule,FormsModule],templateUrl:'./orders.html'})
export class Orders implements OnInit { orders:any[]=[];customers:any[]=[];stores:any[]=[];message=''; order:any={customer:{id:null},store:{id:null},orderDate:'',totalAmount:0,status:'PLACED'};
constructor(private http:HttpClient,private cdr:ChangeDetectorRef){} ngOnInit(){this.load();this.http.get<any[]>(`${API}/customers`).subscribe(d=>this.customers=d);this.http.get<any[]>(`${API}/stores`).subscribe(d=>this.stores=d);}
load(){this.http.get<any[]>(`${API}/orders`).subscribe(d=>{this.orders=d;this.cdr.detectChanges()});}
add(){this.http.post(`${API}/orders`,this.order).subscribe({next:()=>{this.message='Order created';this.order={customer:{id:null},store:{id:null},orderDate:'',totalAmount:0,status:'PLACED'};this.load();},error:()=>this.message='Unable to create order'});}
delete(id:number){if(confirm('Delete order?'))this.http.delete(`${API}/orders/${id}`,{responseType:'text'}).subscribe(()=>this.load());}}
