import { Component,OnInit,ChangeDetectorRef } from '@angular/core'; import { CommonModule } from '@angular/common'; import { FormsModule } from '@angular/forms'; import { HttpClient } from '@angular/common/http'; import { API } from '../api';
@Component({selector:'app-inventory',standalone:true,imports:[CommonModule,FormsModule],templateUrl:'./inventory.html'})
export class Inventory implements OnInit {
 inventory:any[]=[]; low:any[]=[]; products:any[]=[]; stores:any[]=[]; message='';
 item:any={product:{id:null},store:{id:null},quantity:0,minimumStock:0,lastUpdated:''};
 constructor(private http:HttpClient,private cdr:ChangeDetectorRef){}
 ngOnInit(){this.refresh();this.http.get<any[]>(`${API}/products`).subscribe(d=>{this.products=d;this.cdr.detectChanges()});this.http.get<any[]>(`${API}/stores`).subscribe(d=>{this.stores=d;this.cdr.detectChanges()});}
 refresh(){this.http.get<any[]>(`${API}/inventory`).subscribe(d=>{this.inventory=d;this.cdr.detectChanges()});this.http.get<any[]>(`${API}/inventory/low-stock`).subscribe(d=>{this.low=d;this.cdr.detectChanges()});}
 add(){if(!this.item.product.id||!this.item.store.id){this.message='Select product and store';return;} this.http.post(`${API}/inventory`,this.item).subscribe({next:()=>{this.message='Inventory saved';this.item={product:{id:null},store:{id:null},quantity:0,minimumStock:0,lastUpdated:''};this.refresh();},error:()=>this.message='Unable to save inventory'});}
 delete(id:number){if(confirm('Delete inventory record?'))this.http.delete(`${API}/inventory/${id}`,{responseType:'text'}).subscribe(()=>this.refresh());}
}