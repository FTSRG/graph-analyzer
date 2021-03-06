TypedEdgeVisualization <- function(edge.type, basic.information, figures.path){
  ## how many edge types do they have?
  number.of.types <- edge.type[,  list(NumberOfTypes=length(Instance)), by = c("file.name", "GraphType")]
  number.of.types <- merge(basic.information, number.of.types, by = c("file.name", "GraphType"))
  PlotsScatterplotByFileName(number.of.types, paste0(figures.path, "numberoftypes.pdf"),
                     x = "NumberOfEdges", y = "NumberOfTypes", col = "GraphType", label = "file.name")
  
  lapply(seq(4, length(edge.type)), function(col.ind){
    # PlotsHistogramByFileName(dt = edge.type,
    #                          plot.file.name = paste0(figures.path, colnames(edge.type)[col.ind], ".pdf"),
    #                          x = colnames(edge.type)[col.ind],
    #                          fill = "GraphType",
    #                          facetwrap = "file.name",
    #                          scalex = T)
    PlotsECDFByFileName(dt = edge.type,
                             plot.file.name = paste0(figures.path, colnames(edge.type)[col.ind], "_ecdf_faceted.pdf"),
                             x = colnames(edge.type)[col.ind],
                             col = "GraphType",
                             facetwrap = "GraphType",
                             scalex = F,
                             title = colnames(edge.type)[col.ind])
    PlotsECDFByFileNameOneSide(dt = edge.type,
                        plot.file.name = paste0(figures.path, colnames(edge.type)[col.ind], "_ecdf_oneside.pdf"),
                        x = colnames(edge.type)[col.ind],
                        col = "GraphType",
                        scalex = F,
                        title = colnames(edge.type)[col.ind])
    })
  
  #edge.type$ratio <- edge.type$TypeActivity / edge.type$NumberOfTypedEdges
#  PlotsBasicScatterplot(edge.type, paste0(figures.path, "TypeActivityratio.pdf"), x = "EdgeTypeConnectivity", y = "NodeToEdgeRatioByType", col = "GraphType", facetwrap = "file.name", scalex = F)
}
