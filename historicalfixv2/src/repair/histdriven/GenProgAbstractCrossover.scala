package repair.histdriven

import java.util
import java.util.{Collections, Random}

import org.uncommons.maths.number.NumberGenerator
import org.uncommons.maths.random.Probability
import org.uncommons.watchmaker.framework.EvolutionaryOperator

/**
 * Created by xuanbach32bit on 4/22/15.
 */
abstract class GenProgAbstractCrossover[T] (crossoverPointsVariable: NumberGenerator[Integer], crossoverProbabilityVariable: NumberGenerator[Probability]) extends EvolutionaryOperator [T] {

/**
 * Sets up a fixed-point cross-over implementation.  Cross-over is
 * applied to all pairs of parents.  To apply cross-over only to a
 * proportion of parent pairs, use the {@link #AbstractCrossover(int, Probability)}
 * constructor.
 * @param crossoverPoints The constant number of cross-over points
 *                        to use for all cross-over operations.
 */
//  def this (crossoverPoints: Integer) {
//    this(new ConstantGenerator[Integer](crossoverPoints), new ConstantGenerator[Probability](Probability.ONE))
//  }

//  def this(){
//   this(new ConstantGenerator[Integer](1),new ConstantGenerator[Probability](Probability.ONE))
//  }
/**
 * Sets up a cross-over implementation that uses a variable number of cross-over
 * points.  Cross-over is applied to all pairs of parents.  To apply cross-over
 * only to a proportion of parent pairs, use the
 * {@link #AbstractCrossover(NumberGenerator, NumberGenerator)} constructor.
 * @param crossoverPointsVariable A random variable that provides a number
 *                                of cross-over points for each cross-over operation.
 */
//  protected def this (crossoverPointsVariable: NumberGenerator[Integer]) {
//    this (crossoverPointsVariable, new ConstantGenerator[Probability](Probability.ONE) )
//  }

/**
 * Sets up a cross-over implementation that uses a variable number of cross-over
 * points.  Cross-over is applied to a proportion of selected parent pairs, with
 * the remainder copied unchanged into the output population.  The size of this
 * evolved proportion is controlled by the {@code crossoverProbabilityVariable}
 * parameter.
 * @param crossoverPointsVariable A variable that provides a (possibly constant,
 *                                possibly random) number of cross-over points for each cross-over operation.
 * @param crossoverProbabilityVariable A variable that controls the probability
 *                                     that, once selected, a pair of parents will be subjected to cross-over rather
 *                                     than being copied, unchanged, into the output population.
 */

/**
 * Applies the cross-over operation to the selected candidates.  Pairs of
 * candidates are chosen randomly and subjected to cross-over to produce
 * a pair of offspring candidates.
 * @param selectedCandidates The evolved individuals that have survived to
 *                           be eligible to reproduce.
 * @param rng A source of randomness used to determine the location of
 *            cross-over points.
 * @return The combined set of evolved offspring generated by applying
 *         cross-over to the the selected candidates.
 */
  def apply (selectedCandidates: util.List[T], rng: Random): util.List[T] = {
    import scala.collection.JavaConversions._
    val selectionClone: util.List[T] = new util.ArrayList[T] (selectedCandidates)
    Collections.shuffle (selectionClone, rng)
    val result: util.List[T] = new util.ArrayList[T] (selectedCandidates.size)
    val iterator: util.Iterator[T] = selectionClone.iterator
    while (iterator.hasNext) {
      val parent1: T = iterator.next
      if (iterator.hasNext) {
        val parent2: T = iterator.next
        val crossoverPoints: Int = if (crossoverProbabilityVariable.nextValue.nextEvent (rng) ) crossoverPointsVariable.nextValue else 0
        if (crossoverPoints > 0) {
          result.addAll (mate (parent1, parent2, crossoverPoints, rng) )
        }
        else {
          result.add (parent1)
          result.add (parent2)
        }
      }
      else {
        result.add (parent1)
      }
    }
    return result
  }

/**
 * Perform cross-over on a pair of parents to generate a pair of offspring.
 * @param parent1 One of two individuals that provides the source material
 *                for generating offspring.
 * @param parent2 One of two individuals that provides the source material
 *                for generating offspring.
 * @param numberOfCrossoverPoints The number of cross-overs performed on the
 *                                two parents.
 * @param rng A source of randomness used to determine the location of
 *            cross-over points.
 * @return A list containing two evolved offspring.
 */
protected def mate (parent1: T, parent2: T, numberOfCrossoverPoints: Int, rng: Random): List[T]
}