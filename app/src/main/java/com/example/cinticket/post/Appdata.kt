package com.example.cinticket.post

import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class Appdata {
    companion object {
        const val senderEmailAddress = "cinticketcompany@gmail.com"
        const val senderEmailPassword = "haamfcmpxfrgamth"

        //const val receiverEmailAddress = "georgejiadze@gmail.com"
        private const val gmailHost = "smtp.gmail.com"


        fun sendEmailAboutReg(
            from: String,
            theme: String,
            text: String,
            receiverEmailAddress: String
        ) {
            val properties = System.getProperties()

            properties["mail.smtp.host"] = gmailHost

            properties["mail.smtp.port"] = "465"

            properties["mail.smtp.ssl.enable"] = "true"

            properties["mail.smtp.auth"] = "true"
            val session = Session.getInstance(properties, object : Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(
                        senderEmailAddress,
                        senderEmailPassword
                    )
                }
            })

            val message = MimeMessage(session)
            try {

                message.addRecipient(
                    Message.RecipientType.TO,
                    InternetAddress(receiverEmailAddress)
                )
                message.subject = theme

                message.setText("From: $from\n$text")

                val thread = Thread {
                    try {
                        Transport.send(message)
                    } catch (e: MessagingException) {
                        e.printStackTrace()
                    }
                }
                thread.start()
            } catch (e: MessagingException) {
                throw RuntimeException(e)
            }
        }


        fun sendEmailTickets(
            from: String,
            theme: String,
            text: ArrayList<Int>,
            receiverEmailAddress: String
        ) {
            val properties = System.getProperties()

            properties["mail.smtp.host"] = gmailHost

            properties["mail.smtp.port"] = "465"

            properties["mail.smtp.ssl.enable"] = "true"

            properties["mail.smtp.auth"] = "true"
            val session = Session.getInstance(properties, object : Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(
                        senderEmailAddress,
                        senderEmailPassword
                    )
                }
            })

            val message = MimeMessage(session)
            try {

                message.addRecipient(
                    Message.RecipientType.TO,
                    InternetAddress(receiverEmailAddress)
                )
                message.subject = theme
                var tmp = ""
                for (i in 0 until text.size) {
                    tmp += "Ряд: ${text[i] / 6 + 1}, Место: ${text[i] % 6}\n"
                }
                message.setText("From: $from\n$tmp")
                val thread = Thread {
                    try {
                        Transport.send(message)
                    } catch (e: MessagingException) {
                        e.printStackTrace()
                    }
                }
                thread.start()
            } catch (e: MessagingException) {
                throw RuntimeException(e)
            }
        }
    }
}