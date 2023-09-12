export interface Notification {
    id: number;
    orderId: number;
    type: string;
    dateTime: Date;
    read: boolean;
};