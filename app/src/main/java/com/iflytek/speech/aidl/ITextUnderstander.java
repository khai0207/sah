package com.iflytek.speech.aidl;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.iflytek.speech.TextUnderstanderListener;

/* loaded from: classes.dex */
public interface ITextUnderstander extends IInterface {

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ITextUnderstander {
        private static final String DESCRIPTOR = "com.iflytek.speech.aidl.ITextUnderstander";
        static final int TRANSACTION_cancel = 2;
        static final int TRANSACTION_isUnderstanding = 3;
        static final int TRANSACTION_understandText = 1;

        /* loaded from: classes.dex */
        private static class Proxy implements ITextUnderstander {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.iflytek.speech.aidl.ITextUnderstander
            public void cancel(TextUnderstanderListener textUnderstanderListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(textUnderstanderListener != null ? textUnderstanderListener.asBinder() : null);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.iflytek.speech.aidl.ITextUnderstander
            public boolean isUnderstanding() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.iflytek.speech.aidl.ITextUnderstander
            public void understandText(Intent intent, TextUnderstanderListener textUnderstanderListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(textUnderstanderListener != null ? textUnderstanderListener.asBinder() : null);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITextUnderstander asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ITextUnderstander)) ? new Proxy(iBinder) : (ITextUnderstander) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                understandText(parcel.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(parcel) : null, TextUnderstanderListener.Stub.asInterface(parcel.readStrongBinder()));
            } else {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 1598968902) {
                            return super.onTransact(i, parcel, parcel2, i2);
                        }
                        parcel2.writeString(DESCRIPTOR);
                        return true;
                    }
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isUnderstanding = isUnderstanding();
                    parcel2.writeNoException();
                    parcel2.writeInt(isUnderstanding ? 1 : 0);
                    return true;
                }
                parcel.enforceInterface(DESCRIPTOR);
                cancel(TextUnderstanderListener.Stub.asInterface(parcel.readStrongBinder()));
            }
            parcel2.writeNoException();
            return true;
        }
    }

    void cancel(TextUnderstanderListener textUnderstanderListener) throws RemoteException;

    boolean isUnderstanding() throws RemoteException;

    void understandText(Intent intent, TextUnderstanderListener textUnderstanderListener) throws RemoteException;
}
