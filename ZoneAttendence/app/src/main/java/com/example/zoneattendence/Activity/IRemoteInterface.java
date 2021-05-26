package com.example.zoneattendence.Activity;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IRemoteInterface extends IInterface {

    FamocoDevice getFamocoDevice() throws RemoteException;

    public abstract static class Stub extends Binder implements IRemoteInterface {
        private static final String DESCRIPTOR = "com.famoco.ipc.IRemoteInterface";
        static final int TRANSACTION_getFamocoDevice = 1;

        public Stub() {
            this.attachInterface(this, "com.famoco.ipc.IRemoteInterface");
        }

        public static IRemoteInterface asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            } else {
                IInterface iin = obj.queryLocalInterface("com.famoco.ipc.IRemoteInterface");
                return (IRemoteInterface)(iin != null && iin instanceof IRemoteInterface ? (IRemoteInterface)iin : new IRemoteInterface.Stub.Proxy(obj));
            }
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch(code) {
                case 1:
                    data.enforceInterface("com.famoco.ipc.IRemoteInterface");
                    FamocoDevice _result = this.getFamocoDevice();
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(1);
                        _result.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }

                    return true;
                case 1598968902:
                    reply.writeString("com.famoco.ipc.IRemoteInterface");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IRemoteInterface {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "com.famoco.ipc.IRemoteInterface";
            }

            public FamocoDevice getFamocoDevice() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                FamocoDevice _result;
                try {
                    _data.writeInterfaceToken("com.famoco.ipc.IRemoteInterface");
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    if (0 != _reply.readInt()) {
                        _result = (FamocoDevice)FamocoDevice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

                return _result;
            }
        }
    }
}
