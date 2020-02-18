package com.traiingtrack.top10dowloader

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity";
    private val URL = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate called")
        val downloadData = DownloadData()
        downloadData.execute(URL)
        Log.d(TAG, "onCreate: done")
    }

    companion object {
        private class DownloadData : AsyncTask<String, Void, String>() {
            private val TAG = "DownloadData"

            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                Log.d(TAG, "onPostExecute called: paramete is $result")
            }

            override fun doInBackground(vararg url: String?): String {
                Log.d(TAG, "doInBackground called: starts with ${url[0]}")
                val rssFeed = downloadXML(url[0])
                if (rssFeed.isEmpty()) {
                    Log.e(TAG, "doInBackground: Error downloading")
                }
                return rssFeed
            }

            private fun downloadXML(urlPath: String?): String {
                val TAG = "downloadXML"
                val xmlResult = StringBuilder()

                try {
                    val url = URL(urlPath)
                    val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                    val response = connection.responseCode
                    Log.d(TAG, "downloadXML: The respose code was $response")

                    connection.inputStream.buffered().reader().use { xmlResult.append(it.readText()) }

                    Log.d(TAG, "Received ${xmlResult.length} bytes")
                    return xmlResult.toString()


//                } catch (e: MalformedURLException) {
//                    Log.e(TAG, "downloadXML: Invalid URL ${e.message}")
//                } catch (e: IOException) {
//                    Log.e(TAG, "downloadXML: IO Exception reading data ${e.message}")
//                } catch (e: SecurityException) {
//                    Log.e(TAG, "downloadXML: Security exception...needs permission: ${e.message}")
//                } catch (e: Exception) {
//                    Log.e(TAG, "Unknown error: ${e.message}")
//                }

                }catch (e: Exception){
                    val TAG = "downloadXML:"
                    val errorMessage: String = when (e){
                        is MalformedURLException -> "$TAG invalid URL ${e.message}"
                        is IOException -> "$TAG IO Exception reading data ${e.message}"
                        is SecurityException -> {
                            e.printStackTrace()
                           "$TAG Security Exception. Needs permission ${e.message}"
                        }
                        else -> "$TAG Unknown error: ${e.message}"
                    }

            }
                return ""
            }
        }
}






}
