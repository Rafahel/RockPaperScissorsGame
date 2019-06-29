import java.lang.Exception

class WrongNumberOfPlayersError(message: String) : IndexOutOfBoundsException(message)

class NoSuchStrategyError(message: String) : Exception(message)

class Jogador(val nome: String, val jogada: String)

fun main() {
    // Teste de envio de apenas um jogador
//    rps_game_winner(arrayOf(Jogador("Solitário", "P")))
    // Teste de envio de uma jogada que não existe no jogo
//    rps_game_winner(arrayOf(Jogador("Player 1", "l"), Jogador("Player 2", "P")))

    val torneio: MutableList<Jogador> = mutableListOf()
    torneio.add(Jogador("Armando", "P"))
    torneio.add(Jogador("Dave", "S"))
    torneio.add(Jogador("Richard", "R"))
    torneio.add(Jogador("Michael", "S"))
    torneio.add(Jogador("Allen", "S"))
    torneio.add(Jogador("Omer", "P"))
    torneio.add(Jogador("David E.", "R"))
    torneio.add(Jogador("Richard X.", "P"))

    // Inicio do torneio
    while (torneio.size > 1 ) {
        val qtdRounds = torneio.size / 2
        for (i in 0..qtdRounds) {
            if(i == qtdRounds) {
                break
            }
            val jogadorA = torneio[i]
            val jogadorB = torneio[i +1]
//            println("${jogadorA.nome} VS ${jogadorB.nome}")
            if(rps_game_winner(arrayOf(jogadorA, jogadorB)) == jogadorA) {
                println("${jogadorA.nome} ganha de ${jogadorB.nome}")
                torneio.remove(jogadorB)
            } else {
                println("${jogadorB.nome} ganha de ${jogadorA.nome}")
                torneio.remove(jogadorA)
            }
        }
    }
    println("\nTorneio finalizado: ${torneio[0].nome} Ganha o torneio")


}

fun rps_game_winner(jogadores: Array<Jogador>): Jogador {


    if (jogadores.size != 2) {
        throw WrongNumberOfPlayersError("Erro no num de jogadores")
    }
    val jogadorA = jogadores[0]
    val jogadorB = jogadores[1]
    try {

        checkJogada(jogadorA, jogadorB)
        if (jogadorA.jogada == jogadorB.jogada) {
            return jogadorA
        }

        // Realiza a checagem de todas as possibilidades do jogador 1 ganhar, caso não passe em nenhuma
        // o jogador 2 ganha
        if (jogadorA.jogada == "R") {
            if (jogadorB.jogada == "S") {
                return jogadorA
            }
        } else if (jogadorA.jogada == "P") {
            if (jogadorB.jogada == "R") {
                return jogadorA
            }
        } else if (jogadorA.jogada == "S") {
            if (jogadorB.jogada == "P") {
                return jogadorA
            }
        }
        return jogadorB
    } catch (e: NoSuchStrategyError) {
        throw e
    } catch (e: WrongNumberOfPlayersError) {
        throw e
    } catch (e: Exception) {
        throw e
    }
}

fun checkJogada(jogadorA: Jogador, jogadorB: Jogador) {
    val jogadasPossiveis = arrayOf("R", "P", "S")

    if (!(jogadasPossiveis.contains(jogadorA.jogada) && jogadasPossiveis.contains(jogadorB.jogada))) {
        throw NoSuchStrategyError("NoSuchStrategyError")
    }
}