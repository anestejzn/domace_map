import { DataSource } from "@angular/cdk/collections";
import { ReplaySubject, Observable } from "rxjs";
import { OfferOrder } from "./order";

export class OfferDataSource extends DataSource<OfferOrder> {
    private _dataStream = new ReplaySubject<OfferOrder[]>();
  
    constructor(initialData: OfferOrder[]) {
      super();
      this.setData(initialData);
    }
  
    connect(): Observable<OfferOrder[]> {
      return this._dataStream;
    }
  
    disconnect() {}
  
    setData(data: OfferOrder[]) {
      this._dataStream.next(data);
    }
  }