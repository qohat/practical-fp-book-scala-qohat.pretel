package com.qohat

import io.estatico.newtype.macros.newtype
import java.util.UUID
import com.qohat.user.UserId
import squants.market.Money

package object payment {
    @newtype case class PaymentId(value: UUID)
    @newtype case class CardName(value: String)
    @newtype case class CardNumber(value: Long)
    @newtype case class CardExpiration(value: String)
    @newtype case class CardCvv(value: String)
    case class Card(name: CardName, number: CardNumber, expiration: CardExpiration, cvv: CardCvv)
    case class Payment(userId: UserId, total: Money, card: Card)
}