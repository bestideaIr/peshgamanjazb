package ir.b3stidea.peshgamanjazb;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import com.downloader.PRDownloader;
import com.downloader.Status;
import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.Progress;

class B_DownloadManager {
    //interface
    interface OnCompleteListner {
        void onComplete();
    }

    interface OnErrorListner {
        void onError(Error error);
    }

    interface OnCancelListner {
        void onCancel();
    }

    interface OnDownloadProgressListener {
        void onProgress(int progress);
    }


    //variabels
    Activity activity;
    String path;
    String url;
    String file_name;
    OnCompleteListner listner_complete;
    OnErrorListner listner_error;
    OnCancelListner listner_Cancel;
    OnDownloadProgressListener listner_progres;

    int downloadIdTwo;
    ProgressDialog progressBar;
    private boolean download_cancel;
    private boolean bol_compelete;
    boolean is_show_dialog;
    //functions

    public B_DownloadManager(Activity activity, boolean is_show_dialog) {
        this.activity = activity;
        this.is_show_dialog = is_show_dialog;
    }

    public B_DownloadManager setPath(String path) {
        this.path = path;
        return this;
    }

    public B_DownloadManager setDownload(String url, String file_name) {
        this.url = url;
        this.file_name = file_name;
        return this;
    }

    public B_DownloadManager setDownload(String url) {
        this.url = url;
        this.file_name = url.substring(url.lastIndexOf('/') + 1);
        return this;
    }

    public B_DownloadManager setOnCompleteListner(OnCompleteListner listner) {
        this.listner_complete = listner;
        return this;

    }

    public B_DownloadManager setOnErrorListner(OnErrorListner listner) {
        this.listner_error = listner;
        return this;
    }

    public B_DownloadManager setOnCancelListner(OnCancelListner listner) {
        this.listner_Cancel = listner;
        return this;
    }

    public B_DownloadManager setOnDownloadProgressListener(OnDownloadProgressListener listner_progres) {
        this.listner_progres = listner_progres;
        return this;
    }


    public void start() {

        progressBar = new ProgressDialog(activity);
        progressBar.setCancelable(true);
        progressBar.setMessage("در حال دانلود ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressBar.setProgress(0);
        progressBar.setMax(100);


        if (Status.RUNNING == PRDownloader.getStatus(downloadIdTwo)) {
            PRDownloader.pause(downloadIdTwo);
            return;
        }

        if (Status.PAUSED == PRDownloader.getStatus(downloadIdTwo)) {
            PRDownloader.resume(downloadIdTwo);
            return;
        }

        download_cancel = false;
        bol_compelete = false;

        downloadIdTwo = PRDownloader.download(url, path, file_name)
                .build()
                .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                    @Override
                    public void onStartOrResume() {
                    }
                })
                .setOnPauseListener(new OnPauseListener() {
                    @Override
                    public void onPause() {
                    }
                })
                .setOnCancelListener(new OnCancelListener() {
                    @Override
                    public void onCancel() {
                        try {
                            listner_Cancel.onCancel();
                        } catch (Exception e) {

                        }

                        downloadIdTwo = 0;
                    }
                })
                .setOnProgressListener(new OnProgressListener() {
                    @Override
                    public void onProgress(Progress progress) {
                        if (download_cancel == false) {
                            long progressPercent = progress.currentBytes * 100 / progress.totalBytes;

//                            notificationBuilder.setProgress(100, (int) progressPercent, false);
                            listner_progres.onProgress((int) progressPercent);
                            if (is_show_dialog) {
                                progressBar.setProgress((int) progressPercent);
                                if (progressBar.isShowing() == false) {
                                    progressBar.show();
                                }
                            }
                        }
                    }
                })
                .start(new OnDownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        bol_compelete = true;
                        try {
                            listner_complete.onComplete();
                        } catch (Exception e) {

                        }

                        Toast.makeText(activity, "دانلود با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                        progressBar.dismiss();

                    }

                    @Override
                    public void onError(Error error) {
                        download_cancel = true;
                        downloadIdTwo = 0;
                        try {
                            listner_error.onError(error);
                        } catch (Exception e) {

                        }

                    }


                });


        progressBar.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                download_cancel();
            }
        });
    }

    public void download_cancel() {
        PRDownloader.cancel(downloadIdTwo);
        download_cancel = true;
        progressBar.dismiss();
        if (bol_compelete == false) {
            Toast.makeText(activity, "دانلود متوقف شد!", Toast.LENGTH_SHORT).show();
        }
    }


}
