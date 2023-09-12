import { DataSource } from "@angular/cdk/collections";
import { ReplaySubject, Observable } from "rxjs";
import { Order } from "./order";

export class OrderDataSource extends DataSource<Order> {
    private _dataStream = new ReplaySubject<Order[]>();
  
    constructor(initialData: Order[]) {
      super();
      this.setData(initialData);
    }
  
    connect(): Observable<Order[]> {
      return this._dataStream;
    }
  
    disconnect() {}
  
    setData(data: Order[]) {
      this._dataStream.next(data);
    }
  }