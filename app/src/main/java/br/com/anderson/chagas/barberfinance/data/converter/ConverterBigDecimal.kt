package br.com.anderson.chagas.barberfinance.data.converter

import androidx.room.TypeConverter
import java.math.BigDecimal

class ConverterBigDecimal {

    @TypeConverter
    fun fromDoubleToBigDecimal(value : Double?) : BigDecimal? {
        return if (value == null) null else BigDecimal(value)
    }

    @TypeConverter
    fun bigDecimalToDouble(value : BigDecimal?) : Double? {
        return value?.toDouble()
    }
}
