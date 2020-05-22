package hai.exam1.service.impl;

import hai.exam1.model.ImageView;
import hai.exam1.model.Product;
import hai.exam1.repository.ImageViewRepository;
import hai.exam1.service.ImageViewServices;
import org.springframework.beans.factory.annotation.Autowired;

public class ImageViewServiceImpl implements ImageViewServices {
    @Autowired
    private ImageViewRepository imageViewRepository;
    @Override
    public Iterable<ImageView> findAll() {
        return imageViewRepository.findAll();
    }

    @Override
    public ImageView findById(Long id) {
        return imageViewRepository.findById(id).orElse(null);
    }


    @Override
    public void save(ImageView imageViewy) {
imageViewRepository.save(imageViewy);
    }

    @Override
    public void delete(Long id) {
imageViewRepository.deleteById(id);
    }

    @Override
    public Iterable<ImageView> findAllByProduct(Product product) {
        return imageViewRepository.findAllByProduct(product);
    }
}
