import { DataSource } from "@angular/cdk/collections";
import { ReplaySubject, Observable } from "rxjs";
import { Item } from "./item";

export class CartDataSource extends DataSource<Item> {
    private _dataStream = new ReplaySubject<Item[]>();
  
    constructor(initialData: Item[]) {
      super();
      this.setData(initialData);
    }
  
    connect(): Observable<Item[]> {
      return this._dataStream;
    }
  
    disconnect() {}
  
    setData(data: Item[]) {
      this._dataStream.next(data);
    }
  }