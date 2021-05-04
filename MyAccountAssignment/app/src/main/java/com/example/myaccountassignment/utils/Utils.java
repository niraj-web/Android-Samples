package com.example.myaccountassignment.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utils {

    public static final String OTP_DELIMITER = ":";
    public static final String DATE_FORMAT_DMY = "dd-MM-yyyy";
    public static final String BASE_URL = "http://dev.maxdigi.co/";
    public static final String IP_LOCATION_URL = "https://ifcfg.me/";
    public static String NO_INTERNET_MSG = "You don't have internet connection.Please connect to internet";
    public static String NO_INTERNET_TITLE = "No Internet Connection";
    public static String SPECIAL_CHARACTERS = "!@#$%^&*()~`-=_+[]{}|:\";',./<>?";
    public static int MAX_PENDING_MINUTE = 5;
    public static int MOBILE_NO_LENGTH = 10;
    public static int MAX_MINUTE = 61;
    public static String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static String MXPRM = "MXPRM";
    public static String UNPROPER_RESPONSE = "Unable to process your request. Please, Try again later.";
    public static int ADD_EXERCISE = 11;
    private static String tag = "MyFragment";
    private Context context;

    public Utils(Context context) {
        this.context = context;
    }

    public static String getCurrentDateTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return df.format(c.getTime());
    }

    /**
     * Get IP address from first non-localhost interface
     *
     * @return address or empty string
     */
    public static String getIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                        boolean isIPv4 = sAddr.indexOf(':') < 0;

                        if (useIPv4) {
                            if (isIPv4)
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                return delim < 0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } // for now eat exceptions
        return "";
    }

    public static String capitalizeFirstLetter(String str) {
        try {
            if (str != null && !TextUtils.isEmpty(str)) {
                String[] strArray = str.split(" ");
                StringBuilder builder = new StringBuilder();
                for (String s : strArray) {
                    String cap = s.substring(0, 1).toUpperCase() + s.substring(1);
                    builder.append(cap).append(" ");
                }
                return builder.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static Date addDays(Date date, int days) {
        days = days - 1;         //To manage package duration(30 days) i.e. start date 17 Nov & end date should be 16 Nov
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, days); //minus number would decrement the days
        return cal.getTime();
    }

    public static Date substractDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -days); //minus number would decrement the days
        return cal.getTime();
    }

    public static void startActivity(Context context, Class<?> activity) {
        Intent intent = new Intent(context,activity);
        context.startActivity(intent);
    }

    public static String dmyTod(String str_date) {
        SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        SimpleDateFormat targetFormat = new SimpleDateFormat("dd");
        Date date = null;
        try {
            date = originalFormat.parse(str_date); //2017-07-27
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return targetFormat.format(date);
    }

    public static String ymdTod(String str_date) {
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        SimpleDateFormat targetFormat = new SimpleDateFormat("dd");
        Date date = null;
        try {
            date = originalFormat.parse(str_date); //2017-07-27
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return targetFormat.format(date);
    }

    public static String ymdTodmy(String str_date) {
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        SimpleDateFormat targetFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = originalFormat.parse(str_date); //2017-07-27
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return targetFormat.format(date);
    }

  /*  public static Drawable changeVectorColor(Context mContext, View view, int colorId) {
        Drawable background = view.getBackground();
        background.setColorFilter(ContextCompat.getColor(mContext, colorId), PorterDuff.Mode.SRC_IN);
        return background;
    }*/

/*
    public static void closeApp(final Context mContext) {
        final Dialog dialog = new Dialog(mContext, android.R.style.Theme_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialog.findViewById(R.id.btn_ok).setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                ((Activity) mContext).finish();
            }
        });
        dialog.findViewById(R.id.btn_cancel).setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
*/
//
//    public static Dialog showCustomDialog(Context mContext, String content, String okText, String cancelText,
//                                          View.OnClickListener okClickListener, View.OnClickListener cancelClickListener) {
//        final Dialog dialog = new Dialog(mContext, android.R.style.Theme_Dialog);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.custom_dialog);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setCancelable(false);
//        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//        ((TextView) dialog.findViewById(R.id.tv_content)).setText(content);
//        dialog.findViewById(R.id.btn_ok).setVisibility(okText.isEmpty() ? View.GONE : View.VISIBLE);
//        dialog.findViewById(R.id.btn_cancel).setVisibility(cancelText.isEmpty() ? View.GONE : View.VISIBLE);
//        dialog.findViewById(R.id.btn_ok).setOnClickListener(okClickListener);
//        dialog.findViewById(R.id.btn_cancel).setOnClickListener(cancelClickListener);
//        dialog.show();
//        return dialog;
//    }
//
//    public static Dialog showSimpleialog(Context mContext, String content, String okText) {
//        final Dialog dialog = new Dialog(mContext, android.R.style.Theme_Dialog);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.custom_dialog);
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.setCancelable(true);
//        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//        ((TextView) dialog.findViewById(R.id.tv_content)).setText(content);
//        dialog.findViewById(R.id.btn_ok).setVisibility(okText.isEmpty() ? View.GONE : View.VISIBLE);
//        dialog.findViewById(R.id.btn_cancel).setVisibility(View.GONE);
//        dialog.findViewById(R.id.btn_ok).setOnClickListener(view -> dialog.dismiss());
//        dialog.show();
//        return dialog;
//    }

    public static int getDpInPx(Context mContext, float val) {
        Resources r = mContext.getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, val, r.getDisplayMetrics());
    }

    public static List<String> getDates(String dateString1, String dateString2) {
        List<String> datesStr = new ArrayList<>();
        ArrayList<Date> dates = new ArrayList<Date>();
        DateFormat df1 = new SimpleDateFormat("dd/MM/yy");

        Date date1 = null;
        Date date2 = null;

        try {
            date1 = df1.parse(dateString1);
            date2 = df1.parse(dateString2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        while (!cal1.after(cal2)) {
            dates.add(cal1.getTime());
            cal1.add(Calendar.DATE, 1);
        }
        for (int i = 0; i < dates.size(); i++) {
            String format = df1.format(dates.get(i));
            datesStr.add(format);
        }
        return datesStr;
    }

    public static long getDaysBetweenDates(String start, String end) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
        Date startDate, endDate;
        long numberOfDays = 0;
        try {
            startDate = dateFormat.parse(start);
            endDate = dateFormat.parse(end);
            numberOfDays = getUnitBetweenDates(startDate, endDate, TimeUnit.DAYS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return numberOfDays;
    }

    private static long getUnitBetweenDates(Date startDate, Date endDate, TimeUnit unit) {
        long timeDiff = endDate.getTime() - startDate.getTime();
        return unit.convert(timeDiff, TimeUnit.MILLISECONDS);
    }

    public static void alert_dialog(final Context mContext) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(mContext);
        builder1.setTitle("Internet Connection Error");
        builder1.setMessage("Please connect to working Internet connection");
        builder1.setCancelable(true);
        builder1.setPositiveButton ("Go To Settings", new DialogInterface.OnClickListener () {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mContext.startActivity(new Intent(Settings.ACTION_SETTINGS));

            }
        });
        builder1.setNegativeButton ("Cancel", new DialogInterface.OnClickListener () {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder1.show();
    }

    public static String replaceUnwantedChars(String s) {
        if (s.contains("\n")) {
            s = s.replaceAll("\n", "");
        }
        if (s.contains("\r")) {
            s = s.replaceAll("\r", "");
        }
        return s;
    }
//
//    public static void gps_alert_dialog(final Context mContext) {
//        AlertDialog.Builder builder1 = new AlertDialog.Builder(mContext);
////        builder1.setTitle("Internet Connection Error");
//        builder1.setMessage("GPS is not enabled. Do you want to go to settings menu?");
//        builder1.setCancelable(true);
//        builder1.setPositiveButton("Go To Settings", (dialog, id) -> {
//            mContext.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
//            dialog.dismiss();
//        });
//        builder1.setNegativeButton("Cancel", (dialog, id) -> dialog.dismiss());
//        builder1.show();
//    }

    public static void showLongToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showShortToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    // validating email id
    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String parse(float val) {
        DecimalFormat twoDForm = new DecimalFormat("0.00");
        float f = Float.valueOf(twoDForm.format(val));
        String s = String.format("%.02f", f);
        return s;
    }

    /**
     * Mobile No Validation
     *
     * @param no
     * @return true if it is valid else false
     */
    public static boolean isAcceptableMobile(String no) {
        if (TextUtils.isEmpty(no)) {
            System.out.println("empty string.");
            return false;
        }
        no = no.trim();
        int len = no.length();
        if (len < MOBILE_NO_LENGTH || len > MOBILE_NO_LENGTH) {
            System.out.println("Mobile No must have 10 digits");

            return false;
        }
        return true;
    }

/*
    public static void replaceFragment(Activity activity, Fragment frag, boolean flagsAddToBackStack) {
        FragmentManager fm = ((AppCompatActivity) activity).getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
        ft.replace(R.id.fragment_container, frag, tag);
        if (flagsAddToBackStack) {
            if (ft.isAddToBackStackAllowed()) {
                ft.addToBackStack(null);
            }
        }
        ft.commit();
    }
*/

   /* public static void replaceChildFragment(FragmentManager childFragmentManager, Fragment fragment) {
        android.support.v4.app.FragmentTransaction ft = childFragmentManager.beginTransaction();
        ft.replace(R.id.infoFragmentContainer, fragment);
        ft.commit();
    }
*/
    // convert InputStream to String
/*
    public static String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is, StandardCharsets.ISO_8859_1), 8);
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
*/

    public static void CopyStream(InputStream is, OutputStream os) {
        final int buffer_size = 1024;
        try {
            byte[] bytes = new byte[buffer_size];
            for (; ; ) {
                int count = is.read(bytes, 0, buffer_size);
                if (count == -1)
                    break;
                os.write(bytes, 0, count);
            }
        } catch (Exception ex) {
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        if (activity.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(activity
                        .getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    public static void hideSoftKeyboardInFragment(Activity activity) {
        activity.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

//            Log.e("Package Name=", context.getApplicationContext().getPackageName());
            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));
                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
//            Log.e("Name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
//            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
//            Log.e("Exception", e.toString());
        }
        return key;
    }

    public static String checkNotEmpty(String s) {
        if (s != null && !TextUtils.isEmpty(s) && !s.equalsIgnoreCase("null")) {
            return s;
        }
        return "";
    }

    public static String checkForNull(String s) {
        if (s != null && !TextUtils.isEmpty(s) && !s.equalsIgnoreCase("NA") && !s.equalsIgnoreCase("null")) {
            if (s.contains("\n")) {
                s = s.replaceAll("\n", "");
            }
            if (s.contains("\r")) {
                s = s.replaceAll("\r", "");
            }
            return s;
        }
        return "";
    }

    public static String format(Number n) {
        NumberFormat format = DecimalFormat.getInstance();
        format.setRoundingMode(RoundingMode.FLOOR);
        format.setMinimumFractionDigits(0);
        format.setMaximumFractionDigits(2);
        return format.format(n);
    }

  /*  public static void setupUI(final Activity context, final View view) {
        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    com.demo.myapplication.utils.Utils.hideSoftKeyboard(context);
                    v.requestFocus();
                    // et_Searchrest.setError(null);
                    return false;
                }
            });
        }

        // If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(context, innerView);
            }
        }
    }*/

    public static Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 0) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    /*
     * Method for Setting the Height of the ListView dynamically. Hack to fix
	 * the issue of not showing all the items of the ListView when placed inside
	 * a ScrollView
	 */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
                View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    private void tintViewDrawable(Button view, int color) {
        Drawable[] drawables = view.getCompoundDrawables();
        for (Drawable drawable : drawables) {
            if (drawable != null) {
                drawable.mutate();
                drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
            }
        }
    }


}
