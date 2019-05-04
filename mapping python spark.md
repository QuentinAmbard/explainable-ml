
Mapping entre params envoyés à
_cext.dense_tree_shap() coté python
et
org.apache.spark.ml.tree

SHAP                                                 Spark RandomForestClassifier
                                                     
self.model.children_left                             org.apache.spark.ml.tree.Node leftChild
self.model.children_right                            org.apache.spark.ml.tree.Node rightChild
self.model.children_default                          ?
self.model.features                                  ?org.apache.spark.ml.tree.Node.treeParams featuresDataType
self.model.thresholds                                org.apache.spark.ml.tree.Split ContinuousSplit threshold
self.model.values                                    ?
self.model.node_sample_weight                        ?org.apache.spark.ml.tree.Node numDescendants
self.model.max_depth                                 org.apache.spark.ml.tree.Node.treeParams maxDepth
X                                                    fourni par predict()
X_missing                                            fourni par predict()
y                                                    fourni par predict()
self.data                                            fourni par __init__()
self.data_missing                                    fourni par __init__()
tree_limit                                           fourni par shap_values()
self.model.base_offset                               0 (=mean important pour GradientBoostingRegressor?)
phi                                                  là ou mettre le résultat
feature_dependence_codes[self.feature_dependence]    ?"tree_path_dependent"
output_transform_codes[transform]                    org.apache.spark.ml.tree.Node.treeParams GBTRegressorParams lossType
True|False (mode interaction-values ou pas)          