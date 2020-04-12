package br.com.anderson.chagas.barberfinance.model

class PieChartBrain {

    companion object {

        fun calculateProgressPieChart(value : Double, total : Double) : Int {
            val d = value.div(total)
            return  d.times(100).toInt()
        }

    }
}