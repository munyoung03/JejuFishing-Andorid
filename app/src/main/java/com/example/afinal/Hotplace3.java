package com.example.afinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.afinal.fish.Gam;
import com.example.afinal.fish.Stone;
import com.example.afinal.ui.dashboard.DashboardFragment;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Hotplace3 extends AppCompatActivity {
    private static final int REQUEST_ENABLE_BT = 10; // 블루투스 활성화 상태
    private BluetoothAdapter bluetoothAdapter; // 블루투스 어댑터
    private Set<BluetoothDevice> devices; // 블루투스 디바이스 데이터 셋
    private BluetoothDevice bluetoothDevice; // 블루투스 디바이스
    private BluetoothSocket bluetoothSocket = null; // 블루투스 소켓
    private OutputStream outputStream = null; // 블루투스에 데이터를 출력하기 위한 출력 스트림
    private InputStream inputStream = null; // 블루투스에 데이터를 입력하기 위한 입력 스트림
    private Thread workerThread = null; // 문자열 수신에 사용되는 쓰레드
    private byte[] readBuffer; // 수신 된 문자열을 저장하기 위한 버퍼
    private int readBufferPosition; // 버퍼 내 문자 저장 위치
    private TextView hot3_textreceive; // 수신 된 데이터를 표시하기 위한 텍스트 뷰
    private ImageView imageView;
    private ImageButton hotplace3_fish2;
    private ImageButton hotplace3_fish6;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_ENABLE_BT:
                if (requestCode == RESULT_OK) { // '사용'을 눌렀을 때
                    selectBluetoothDevice(); // 블루투스 디바이스 선택 함수 호출
                } else { // '취소'를 눌렀을때
                    Intent intent = new Intent(getApplicationContext(), DashboardFragment.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "블루투스를 연결해주세요", Toast.LENGTH_SHORT).show();
                    // 여기에 처리 할 코드를 작성하세요.
                }
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotplace3);

        hotplace3_fish2 = findViewById(R.id.hotplace3_fish2);
        hotplace3_fish6 = findViewById(R.id.hotplace3_fish6);
        hot3_textreceive = findViewById(R.id.hot3_receive);
        imageView = findViewById(R.id.hot3_weatherimage);

        hotplace3_fish6.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Stone.class);
            startActivity(intent);
        });
        hotplace3_fish2.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Gam.class);
            startActivity(intent);
        });
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter(); // 블루투스 어댑터를 디폴트 어댑터로 설정
        if (bluetoothAdapter == null) { // 디바이스가 블루투스를 지원하지 않을 때
            Intent intent = new Intent(getApplicationContext(), DashboardFragment.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "블루투스를 연결해주세요", Toast.LENGTH_SHORT).show();
        } else { // 디바이스가 블루투스를 지원 할 때
            if (bluetoothAdapter.isEnabled()) { // 블루투스가 활성화 상태 (기기에 블루투스가 켜져있음)
                selectBluetoothDevice(); // 블루투스 디바이스 선택 함수 호출
            } else { // 블루투스가 비 활성화 상태 (기기에 블루투스가 꺼져있음)
                // 블루투스를 활성화 하기 위한 다이얼로그 출력
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                // 선택한 값이 onActivityResult 함수에서 콜백된다.
                startActivityForResult(intent, REQUEST_ENABLE_BT);
            }
        }
    }
    public void selectBluetoothDevice() {
        // 이미 페어링 되어있는 블루투스 기기를 찾습니다.
        devices = bluetoothAdapter.getBondedDevices();
        // 페어링 된 디바이스의 크기를 저장
        int pariedDeviceCount = devices.size();
        // 페어링 되어있는 장치가 없는 경우
        if(pariedDeviceCount == 0) {
            // 페어링을 하기위한 함수 호출
        }
        // 페어링 되어있는 장치가 있는 경우
        else {
            // 디바이스를 선택하기 위한 다이얼로그 생성
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("페어링 되어있는 블루투스 디바이스 목록");
            // 페어링 된 각각의 디바이스의 이름과 주소를 저장
            List<String> list = new ArrayList<>();
            // 모든 디바이스의 이름을 리스트에 추가
            for(BluetoothDevice bluetoothDevice : devices) {
                list.add(bluetoothDevice.getName());
            }
            list.add("취소");
            // List를 CharSequence 배열로 변경
            final CharSequence[] charSequences = list.toArray(new CharSequence[list.size()]);
            list.toArray(new CharSequence[list.size()]);
            // 해당 아이템을 눌렀을 때 호출 되는 이벤트 리스너
            builder.setItems(charSequences, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // 해당 디바이스와 연결하는 함수 호출
                    connectDevice(charSequences[which].toString());
                }
            });
            // 뒤로가기 버튼 누를 때 창이 안닫히도록 설정
            builder.setCancelable(false);
            // 다이얼로그 생성
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
    public void connectDevice(String deviceName) {
        // 페어링 된 디바이스들을 모두 탐색
        for(BluetoothDevice tempDevice : devices) {
            // 사용자가 선택한 이름과 같은 디바이스로 설정하고 반복문 종료
            if(deviceName.equals(tempDevice.getName())) {
                bluetoothDevice = tempDevice;
                break;
            }
        }
        // UUID 생성
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
        // Rfcomm 채널을 통해 블루투스 디바이스와 통신하는 소켓 생성
        try {
            bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
            bluetoothSocket.connect();
            // 데이터 송,수신 스트림을 얻어옵니다.
            outputStream = bluetoothSocket.getOutputStream();
            inputStream = bluetoothSocket.getInputStream();
            // 데이터 수신 함수 호출
            receiveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void receiveData() {
        final Handler handler = new Handler();
        // 데이터를 수신하기 위한 버퍼를 생성
        readBufferPosition = 0;
        readBuffer = new byte[3000];
        // 데이터를 수신하기 위한 쓰레드 생성
        workerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!(Thread.currentThread().isInterrupted())) { try {
                        // 데이터를 수신했는지 확인합니다.
                        int byteAvailable = inputStream.available();
                        // 데이터가 수신 된 경우
                        if(byteAvailable > 0) {
                            Log.println(Log.DEBUG, "CURRENT", "DATA : " + readBufferPosition + " , " + readBuffer.length);
//                            textViewReceive.append("RECEIVED");
                            // 입력 스트림에서 바이트 단위로 읽어 옵니다.
                            byte[] bytes = new byte[byteAvailable];
                            inputStream.read(bytes);
                            Log.println(Log.DEBUG, "sad", "data : " + byteAvailable);
                            // 입력 스트림 바이트를 한 바이트씩 읽어 옵니다.
                            for (int i = 0; i < byteAvailable; i++) {
                                byte tempByte = bytes[i];
                                if (tempByte == '\n') {
                                    // 개행문자를 기준으로 받음(한줄)
                                    Log.println(Log.DEBUG, "SA", "DATA : " + tempByte);
                                    Log.println(Log.DEBUG, "gggggg", "gggggggggg : " + tempByte);
                                    // readBuffer 배열을 encodedBytes로 복사
                                    byte[] encodedBytes = new byte[readBufferPosition];
                                    System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                                    // 인코딩 된 바이트 배열을 문자열로 변환
                                    final String text = new String(encodedBytes, "US-ASCII");
                                    //Json
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            StringBuilder builder = new StringBuilder();
                                            String arr[] = text.split("\n");

                                            Log.println(Log.DEBUG, "ASG", "ASF : " + arr[0]);
                                            try {
                                                JSONObject obj = new JSONObject(arr[0]);
                                                Log.println(Log.DEBUG, "DATA", obj.get("temp").toString());
                                                Log.println(Log.DEBUG, "DATA", obj.getString("hei"));
                                                Log.println(Log.DEBUG, "DATA", obj.getString("isRain"));
                                                if(obj.getString("isRain").equals("0"))
                                                {
                                                    imageView.setImageResource(R.drawable.sun);
                                                }
                                                else if(obj.getString("isRain").equals("1"))
                                                {
                                                    imageView.setImageResource(R.drawable.rain);
                                                }
                                                hot3_textreceive.setText("    현재온도 : "+obj.get("temp").toString()+"℃"+"\n"+"    "+"현재습도 : "+obj.getString("hei")+"%");
                                            }catch(Exception e){e.printStackTrace();}
                                            //textViewReceive.setText(text + "\n");
                                        }
                                    });
                                    readBufferPosition = 0;
                                } // 개행 문자가 아닐 경우
                                else {
                                    readBuffer[readBufferPosition++] = tempByte;
                                }
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        // 1초마다 받아옴
                        readBufferPosition = 0;
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        workerThread.start();
    }
}

