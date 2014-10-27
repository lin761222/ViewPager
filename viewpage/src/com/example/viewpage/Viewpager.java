package com.example.viewpage;

import org.w3c.dom.Text;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Viewpager extends Activity implements OnPageChangeListener {

	ViewPager viewPager;
	// ���I�I��
	ImageView[] tips;
	// �˹Ϥ���
	ImageView[] mImageViews;
	// �Ϥ��귽id
	int[] imgIdArray;
	// ��r��
	TextView txv;
	String txv_index[] = { "111", "222", "333", "444", "555" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewpager);

		ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);
		viewPager = (ViewPager) findViewById(R.id.viewPager);

		imgIdArray = new int[] { R.drawable.kind_1, R.drawable.kind_2,
				R.drawable.kind_3, R.drawable.kind_4, R.drawable.kind_5 };
		txv = (TextView) findViewById(R.id.txv);

		// �N�I�I�[�J��ViewGroup��
		tips = new ImageView[imgIdArray.length];
		for (int i = 0; i < tips.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setLayoutParams(new LayoutParams(10, 10));
			tips[i] = imageView;
			if (i == 0) {
				tips[i].setBackgroundResource(R.drawable.page);
			} else {
				tips[i].setBackgroundResource(R.drawable.ic_launcher);
			}

			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
					new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT));
			layoutParams.leftMargin = 5;
			layoutParams.rightMargin = 5;
			group.addView(imageView, layoutParams);
		}
		// �N�Ϥ��˸���Ʋդ�
		mImageViews = new ImageView[imgIdArray.length];
		for (int i = 0; i < mImageViews.length; i++) {
			ImageView imageView = new ImageView(this);
			mImageViews[i] = imageView;
			imageView.setBackgroundResource(imgIdArray[i]);
			//�]�w��r
			txv.setText(txv_index[i]);
		}

		// �]�mAdapter
		viewPager.setAdapter(new MyAdapter());
		// �]�m��ť�A�D�n�O�]�m�I�I���I��
		viewPager.setOnPageChangeListener(this);
		// �]�mViewPager���q�{��, �]�m�����ת�100���A�o�ˤl�}�l�N�੹���ư�
		viewPager.setCurrentItem((mImageViews.length*100));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.viewpager, menu);
		return true;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		Log.e("position", arg0+"");
		setImageBackground(arg0 % mImageViews.length);
		setPageText(arg0 % mImageViews.length);
		
	}
	
	private void setPageText(int p){
		txv.setText(txv_index[p]);
	}

	private void setImageBackground(int selectItems) {
		for (int i = 0; i < tips.length; i++) {
			if (i == selectItems) {
				tips[i].setBackgroundResource(R.drawable.page);
			} else {
				tips[i].setBackgroundResource(R.drawable.ic_launcher);
			}
		}
	}

	public class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(mImageViews[position
					% mImageViews.length]);

		}

		/**
		 * ���J�Ϥ��i�h�A�η�e��position ���H�Ϥ��Ʋժ��ר��l�ƬO����
		 */
		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager) container).addView(mImageViews[position
					% mImageViews.length], 0);
			return mImageViews[position % mImageViews.length];
		}

	}

}
