import { Component,OnInit,ChangeDetectorRef } from '@angular/core'; import { CommonModule } from '@angular/common'; import { HttpClient } from '@angular/common/http'; import { API } from '../api';
@Component({selector:'app-dashboard',standalone:true,imports:[CommonModule],templateUrl:'./dashboard.html'})
export class Dashboard implements OnInit { d:any={totalProducts:0,totalOrders:0,totalCustomers:0,lowStockCount:0}; online=false;
constructor(private http:HttpClient,private cdr:ChangeDetectorRef){} ngOnInit(){this.http.get<any>(`${API}/dashboard`).subscribe({next:x=>{this.d=x;this.online=true;this.cdr.detectChanges();},error:()=>{this.online=false;this.cdr.detectChanges();}});}}
