package com.reactlibrary.banks

import com.reactlibrary.interfaces.BankServices
import com.reactlibrary.models.Enums
import com.reactlibrary.models.HoverStrategy


class WemaBank : BaseBank(), BankServices {
    override fun getActionCount(): Int {
        return 2
    }

    override fun getIndex(): Int {
        return index
    }

    override fun setIndex(index: Int): Int {
        Companion.index = index
        return Companion.index
    }

    @Throws(Exception::class)
    override fun getActionByIndex(index: Int): HoverStrategy {
        return when (index) {
            1 -> accountBalance
            2 -> bvn
            else -> accountBalance
        }
    }

    override fun confirmPayment(): HoverStrategy {
        val hoverStrategy = HoverStrategy(
                "9d5a306b",
                "Wema Bank",
                "Verifying transaction",
                10000
        )
        hoverStrategy.id = "verify-payment"
        hoverStrategy.bankResponseMethod = Enums.BankResponseMethod.ussd
        hoverStrategy.requiresPin = true
        return hoverStrategy
    }

    @Throws(Exception::class)
    override fun getNextAction(): HoverStrategy {
        if (index >= actionCount) {
            index = 0
        }
        return getActionByIndex(index + 1)
    }

    override fun hasNext(): Boolean {
        return index < actionCount
    }

    @Throws(Exception::class)
    override fun getBvn(): HoverStrategy {
        return this.getBvn("Wema Bank")
    }

    @Throws(Exception::class)
    override fun getAccounts(): HoverStrategy {
        throw Exception("Not implemented")
    }

    override fun getAccountBalance(): HoverStrategy {
        val hoverStrategy = HoverStrategy(
                "9d5a306b",
                "Wema Bank",
                "Fetching account balance",
                10000
        )
        hoverStrategy.id = "balance"
        hoverStrategy.bankResponseMethod = Enums.BankResponseMethod.ussd
        hoverStrategy.isFirstAction = true
        hoverStrategy.requiresPin = true
        return hoverStrategy
    }

    @Throws(Exception::class)
    override fun getTransactions(): HoverStrategy {
        throw Exception("Not implemented")
    }

    @Throws(Exception::class)
    override fun makePayment(isInternal: Boolean, hasMultipleAccounts: Boolean): HoverStrategy {
        //val actionid = if(isInternal) "61286284" else "6cf43bda"
        val actionid = if(hasMultipleAccounts) "571bb5da" else "6cf43bda"
        val hoverStrategy = HoverStrategy(
                actionid,
                "Wema Bank",
                "Processing Payment",
                0
        )
        hoverStrategy.id = "payment"
        hoverStrategy.requiresPin = true
        hoverStrategy.bankResponseMethod = Enums.BankResponseMethod.ussd
        hoverStrategy.differentActionForInternal = true
        return hoverStrategy
    }

    companion object {
       private var index = 1
    }
}