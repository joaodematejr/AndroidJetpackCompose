package com.demate.nfc_rede.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import rede.smartrede.commons.contract.IConnectorMifare
import rede.smartrede.commons.mifare.MifareKeyType
import rede.smartrede.commons.mifare.MifareResult
import rede.smartrede.sdk.api.IRedeSdk

class ViewModelRede : ViewModel() {

    private var redeNfc: IConnectorMifare? = null

    fun handleInit(context: Context) {
        if (redeNfc == null) {
            val redeSdk = IRedeSdk.newInstance(context)
            val terminalFunctions = redeSdk.getTerminalFunctions()
            redeNfc = terminalFunctions.getConnectorMifare()
            Toast.makeText(context, "Init Context", Toast.LENGTH_SHORT).show()
        }
    }


    private fun bytesToHex(bytes: List<Byte>?): String {
        val builder = StringBuilder()
        if (bytes != null) {
            for (b in bytes) {
                builder.append(Integer.toHexString(b.toInt() and 0xFF))
            }
        }
        return builder.toString()
    }

    fun readNFC(context: Context) {
        if (redeNfc == null) {
            Toast.makeText(context, "Init Context", Toast.LENGTH_SHORT).show()
            return
        }
        Toast.makeText(context, "Aguardando a leitura", Toast.LENGTH_SHORT).show()
        redeNfc?.detectCards { resultDetect ->
            when (resultDetect) {
                is MifareResult.Success -> {
                    val cardInfo: List<Byte>? = resultDetect.cardInfo?.serialNumber
                    if (cardInfo != null) {
                        Toast.makeText(context, "Card detected: ${bytesToHex(cardInfo)}", Toast.LENGTH_SHORT).show()
                    }
                }

                is MifareResult.Error -> {
                    Toast.makeText(
                        context,
                        "Error: ${resultDetect.exception.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }
    }


    private val DEFAULT_KEY_NFC = byteArrayOf(
        0xFF.toByte(),
        0xFF.toByte(),
        0xFF.toByte(),
        0xFF.toByte(),
        0xFF.toByte(),
        0xFF.toByte()
    )
    private val YUZER_KEY_NFC = byteArrayOf(
        0xAF.toByte(),
        0x31.toByte(),
        0x27.toByte(),
        0x01.toByte(),
        0xE8.toByte(),
        0xD5.toByte()
    )

    fun handleRead(context: Context) {
        redeNfc?.detectCards {
            when (it) {
                is MifareResult.Success -> {
                    for (sector in 0 until 16) {
                        for (block in 0 until 4) {
                            //val blockNumber = sector * 4 + block
                            println("Sector $sector, Block $block: ")
                            redeNfc?.authenticateSector(MifareKeyType.A, YUZER_KEY_NFC, sector.toByte()) { authenticationResult ->
                                when (authenticationResult) {
                                    is MifareResult.Success -> {
                                        Toast.makeText(context, "AuthenticateSector Success: ${authenticationResult.toString()}", Toast.LENGTH_SHORT).show()
                                    }
                                    is MifareResult.Error -> {
                                        Toast.makeText(context, "AuthenticateSector Error: ${authenticationResult.exception.message}", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }
                    }

                }
                is MifareResult.Error -> {
                    Toast.makeText(context, "DetectCards Error: ${it.exception.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}