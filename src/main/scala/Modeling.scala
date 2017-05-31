/*
 *
 *  * Copyright 2017 Oliver Weissbarth
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

import de.oweissbarth.bnet.core.BayesianNetwork
import de.oweissbarth.bnet.graph.GraphMLGraphProvider
import de.oweissbarth.bnet.model.{GaussianBaseModelProvider, SimpleCategoricalModelProvider, SimpleLinearModelProvider}
import de.oweissbarth.bnet.sample.CSVSampleProvider

object Modeling extends App{



  val gp = new GraphMLGraphProvider("ageGenderIncome.gml")

  val bn = new BayesianNetwork(gp)

  bn.setModelType("Age", new GaussianBaseModelProvider)
  bn.setModelType("Gender", new SimpleCategoricalModelProvider)
  bn.setModelType("Income", new SimpleLinearModelProvider)



  val sp = new CSVSampleProvider("ageGenderIncome_mediumNoise.csv", ",");



  bn.fit(sp)
  print(bn.asJson())


}
